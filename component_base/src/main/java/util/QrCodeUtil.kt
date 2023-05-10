package util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import com.google.zxing.*
import com.google.zxing.common.GlobalHistogramBinarizer
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.util.*


/**
 * 二维码编/解码工具
 */
object QrCodeUtil {
    private val DECODE_HINTS: MutableMap<DecodeHintType, Any> = EnumMap(DecodeHintType::class.java)
    private val ENCODE_HINTS: MutableMap<EncodeHintType, Any> = EnumMap(EncodeHintType::class.java)

    init {
        val allFormats = ArrayList<BarcodeFormat>()
        allFormats.add(BarcodeFormat.AZTEC)
        allFormats.add(BarcodeFormat.CODABAR)
        allFormats.add(BarcodeFormat.CODE_39)
        allFormats.add(BarcodeFormat.CODE_93)
        allFormats.add(BarcodeFormat.CODE_128)
        allFormats.add(BarcodeFormat.DATA_MATRIX)
        allFormats.add(BarcodeFormat.EAN_8)
        allFormats.add(BarcodeFormat.EAN_13)
        allFormats.add(BarcodeFormat.ITF)
        allFormats.add(BarcodeFormat.MAXICODE)
        allFormats.add(BarcodeFormat.PDF_417)
        allFormats.add(BarcodeFormat.QR_CODE)
        allFormats.add(BarcodeFormat.RSS_14)
        allFormats.add(BarcodeFormat.RSS_EXPANDED)
        allFormats.add(BarcodeFormat.UPC_A)
        allFormats.add(BarcodeFormat.UPC_E)
        allFormats.add(BarcodeFormat.UPC_EAN_EXTENSION)

        DECODE_HINTS[DecodeHintType.TRY_HARDER] = BarcodeFormat.QR_CODE
        DECODE_HINTS[DecodeHintType.POSSIBLE_FORMATS] = allFormats
        DECODE_HINTS[DecodeHintType.CHARACTER_SET] = "utf-8"


        ENCODE_HINTS[EncodeHintType.CHARACTER_SET] = "utf-8"
        ENCODE_HINTS[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
        ENCODE_HINTS[EncodeHintType.MARGIN] = 0
    }

    /**
     * 同步解析本地图片二维码。该方法是耗时操作，请在子线程中调用。
     *
     * @param picturePath 要解析的二维码图片本地路径
     * @return 返回二维码图片里的内容 或 null
     */
    @JvmStatic fun syncDecodeQRCode(picturePath: String): String? {
        return syncDecodeQRCode(getDecodeAbleBitmap(picturePath))
    }

    /**
     * 同步解析bitmap二维码。该方法是耗时操作，请在子线程中调用。
     *
     * @param bitmap 要解析的二维码图片
     * @return 返回二维码图片里的内容 或 null
     */
    @JvmStatic fun syncDecodeQRCode(bitmap: Bitmap?): String? {
        var result: Result? = null
        var source: RGBLuminanceSource? = null
        try {
            val width = bitmap!!.width
            val height = bitmap.height
            val pixels = IntArray(width * height)
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
            source = RGBLuminanceSource(width, height, pixels)
            result = MultiFormatReader().decode(BinaryBitmap(HybridBinarizer(source)), DECODE_HINTS)
            return result!!.text
        } catch (e: Exception) {
            e.printStackTrace()
            if (source != null) {
                try {
                    result = MultiFormatReader().decode(BinaryBitmap(GlobalHistogramBinarizer(source)), DECODE_HINTS)
                    return result!!.text
                } catch (e2: Throwable) {
                    e2.printStackTrace()
                }

            }
            return null
        }

    }

    /**
     * 将本地图片文件转换成可解码二维码的 Bitmap。为了避免图片太大，这里对图片进行了压缩。感谢 https://github.com/devilsen 提的 PR
     *
     * @param picturePath 本地图片文件路径
     * @return
     */
    @JvmStatic  fun getDecodeAbleBitmap(picturePath: String): Bitmap? {
        try {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(picturePath, options)
            var sampleSize = options.outHeight / 400
            if (sampleSize <= 0) {
                sampleSize = 1
            }
            options.inSampleSize = sampleSize
            options.inJustDecodeBounds = false

            return BitmapFactory.decodeFile(picturePath, options)
        } catch (e: Exception) {
            return null
        }

    }


    /**
     * 同步创建黑色前景色、白色背景色的二维码图片。该方法是耗时操作，请在子线程中调用。
     *
     * @param content 要生成的二维码图片内容
     * @param size    图片宽高，单位为px
     */
    @JvmStatic fun syncEncodeQRCode(content: String, size: Int): Bitmap? {
        return syncEncodeQRCode(content, size, Color.BLACK, Color.WHITE, null)
    }

    /**
     * 同步创建指定前景色、白色背景色的二维码图片。该方法是耗时操作，请在子线程中调用。
     *
     * @param content         要生成的二维码图片内容
     * @param size            图片宽高，单位为px
     * @param foregroundColor 二维码图片的前景色
     */
    @JvmStatic fun syncEncodeQRCode(content: String, size: Int, foregroundColor: Int): Bitmap? {
        return syncEncodeQRCode(content, size, foregroundColor, Color.WHITE, null)
    }

    /**
     * 同步创建指定前景色、白色背景色、带logo的二维码图片。该方法是耗时操作，请在子线程中调用。
     *
     * @param content         要生成的二维码图片内容
     * @param size            图片宽高，单位为px
     * @param foregroundColor 二维码图片的前景色
     * @param logo            二维码图片的logo
     */
    @JvmStatic fun syncEncodeQRCode(content: String, size: Int, foregroundColor: Int, logo: Bitmap): Bitmap? {
        return syncEncodeQRCode(content, size, foregroundColor, Color.WHITE, logo)
    }

    /**
     * 同步创建指定前景色、指定背景色、带logo的二维码图片。该方法是耗时操作，请在子线程中调用。
     *
     * @param content         要生成的二维码图片内容
     * @param size            图片宽高，单位为px
     * @param foregroundColor 二维码图片的前景色
     * @param backgroundColor 二维码图片的背景色
     * @param logo            二维码图片的logo
     */
    @JvmStatic fun syncEncodeQRCode(content: String, size: Int, foregroundColor: Int, backgroundColor: Int, logo: Bitmap?): Bitmap? {
        try {
            val matrix = MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, ENCODE_HINTS)
            val pixels = IntArray(size * size)
            for (y in 0 until size) {
                for (x in 0 until size) {
                    if (matrix.get(x, y)) {
                        pixels[y * size + x] = foregroundColor
                    } else {
                        pixels[y * size + x] = backgroundColor
                    }
                }
            }
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, size, 0, 0, size, size)
            return addLogoToQRCode(bitmap, logo)
        } catch (e: Exception) {
            return null
        }

    }

    /**
     * 添加logo到二维码图片上
     *
     * @param src
     * @param logo
     * @return
     */
    private fun addLogoToQRCode(src: Bitmap?, logo: Bitmap?): Bitmap? {
        if (src == null || logo == null) {
            return src
        }

        val srcWidth = src.width
        val srcHeight = src.height
        val logoWidth = logo.width
        val logoHeight = logo.height

        val scaleFactor = srcWidth * 1.0f / 5f / logoWidth.toFloat()
        var bitmap: Bitmap? = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888)
        try {
            val canvas = Canvas(bitmap!!)
            canvas.drawBitmap(src, 0f, 0f, null)
            canvas.scale(scaleFactor, scaleFactor, (srcWidth / 2).toFloat(), (srcHeight / 2).toFloat())
            canvas.drawBitmap(logo, ((srcWidth - logoWidth) / 2).toFloat(), ((srcHeight - logoHeight) / 2).toFloat(), null)
            //TODO
            // canvas.save(Canvas.ALL_SAVE_FLAG)
            canvas.save()
            canvas.restore()
        } catch (e: Exception) {
            bitmap = null
        }

        return bitmap
    }

}



