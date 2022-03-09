package com.cw.plugins.privacyComplianceScan

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.compress.utils.IOUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import java.io.File
import java.io.FileOutputStream
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2022/3/1 下午7:46
 *
 * Description:
 */
class PrivacyComplianceScanTransform : Transform() {
    private lateinit var outputProvider: TransformOutputProvider


    /** 对字节码进行处理的核心 */
    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        PrivacyComplianceScanUtil.printStep("transform")
        transformInvocation?.let {
            val input = it.inputs
            outputProvider = it.outputProvider

            for (item in input) {
                val directoryInput = item.directoryInputs //以源码的方式参与项目编译的所有目录结构及其目录下的源码文件
                val jarInput = item.jarInputs //以jar包方式参与项目编译的所有本地jar包和远程jar包（此处的jar包包括aar）

                if (directoryInput.isNotEmpty()) {
                    for (item in directoryInput) {
                        handleDirectoryInput(item)
                    }
                }

                if (jarInput.isNotEmpty()) {
                    handleJarInput(jarInput)
                }

            }
        }
    }

    /** 定义了Transform的名称 */
    override fun getName(): String {
        return "PrivacyComplianceScanTransform"
    }

    /** 返回当前Transform处理的数据的类型 */
    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType>? {
        return TransformManager.CONTENT_CLASS
    }

    /** 返回当前Transform处理的数据的范围 */
    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /** 放回当前Transform是否开启增量编译 */
    override fun isIncremental(): Boolean {
        return false
    }


    /** 处理:
     *     以源码的方式参与项目编译的所有目录结构及其目录下的源码文件
     */
    private fun handleDirectoryInput(directoryInput: DirectoryInput) {
        if (directoryInput.file.isDirectory && directoryInput.file.listFiles()
                ?.isNotEmpty() == true
        ) {
            for (file in directoryInput.file.listFiles()!!) {
                recursionDir(file)
            }
        }

        val dest = outputProvider.getContentLocation(
            directoryInput.name,
            directoryInput.contentTypes,
            directoryInput.scopes,
            Format.DIRECTORY
        )

        FileUtils.copyDirectory(directoryInput.file, dest)
    }

    private fun recursionDir(file: File) {
        if (file.isDirectory) {
            for (subFile in file.listFiles()) {
                recursionDir(subFile)
            }
        } else {
            if (needDeal(file.name)) {
                transformDirectory(file)
            }
        }
    }

    private fun transformDirectory(inputFile: File) {
        //路径
        val cr = ClassReader(inputFile.readBytes())
        val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
        val cv = ReportPrivacyComplianceClassVisitor(cw)
        cr.accept(cv, ClassReader.EXPAND_FRAMES)

        val bytes = cw.toByteArray()
        val fos =
            FileOutputStream("${inputFile.parentFile.absoluteFile}${File.separator}${inputFile.name}")

        fos.write(bytes)
        fos.close()

    }


    /** 处理:
     *     以jar包方式参与项目编译的所有本地jar包和远程jar包（此处的jar包包括aar）
     *
     *  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *  使用androidx的项目一定也注意jar也需要处理，否则所有的jar都不会最终编译到apk中，千万注意
     *  导致出现ClassNotFoundException的崩溃信息，当然主要是因为找不到父类，因为父类AppCompatActivity在jar中
     */
    private fun handleJarInput(jarInput: Collection<JarInput>) {
        for (item in jarInput) {
//            PrivacyComplianceScanUtil.printJarStep("item.file.absolutePath", item.file.absolutePath)
            if (item.file.absolutePath.endsWith(".jar")) {
                var itemJarName = item.name
                PrivacyComplianceScanUtil.printJarStep("itemJarName", itemJarName)
                val itemMd5name = DigestUtils.md5Hex(item.file.absolutePath)
                if (itemJarName.endsWith(".jar")) {
                    itemJarName = itemJarName.substring(0, itemJarName.length - 4)
                }

                val jarFile = JarFile(item.file)
                val enumeration = jarFile.entries()

                //临时文件存储修改后的文件
                val tempFile = File("${item.file.parent}${File.separator}classes_temp.jar")
                //避免上次的缓存被重复插入
                if (tempFile.exists()) {
                    tempFile.delete()
                }

                val jarOutputStream = JarOutputStream(FileOutputStream(tempFile))

                //遍历jar下面的所有class文件，这里不用考虑当前item是否是Directory的问题
                while (enumeration.hasMoreElements()) {
                    val jarEntry = enumeration.nextElement()
                    val jarEntryName = jarEntry.name
                    PrivacyComplianceScanUtil.printJarEntryName(jarEntryName)

                    val zipEntry = ZipEntry(jarEntryName)
                    val inputStream = jarFile.getInputStream(jarEntry)
                    if (needDeal(jarEntryName)) {
                        //
                        jarOutputStream.putNextEntry(zipEntry)
                        val cr = ClassReader(IOUtils.toByteArray(inputStream))
                        val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
                        val cv = ReportPrivacyComplianceClassVisitor(cw)
                        cr.accept(cv, ClassReader.EXPAND_FRAMES)
                        val code = cw.toByteArray()
                        jarOutputStream.write(code)
                    } else {
                        jarOutputStream.putNextEntry(zipEntry)
                        jarOutputStream.write(IOUtils.toByteArray(inputStream))
                    }
                    jarOutputStream.closeEntry()
                }
                jarOutputStream.close()
                jarFile.close()

                val dest = outputProvider.getContentLocation(
                    "${itemJarName}${itemMd5name}",
                    item.contentTypes,
                    item.scopes,
                    Format.JAR
                )
                FileUtils.copyFile(tempFile, dest)
                tempFile.delete()
            }
        }
    }


    private fun needDeal(name: String): Boolean {
        return (name.endsWith(".class") && name != ("R.class")
                && !name.startsWith("R\$") && name != ("BuildConfig.class"))
    }
}