package com.cw.plugins.channelTag

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.initialization.DefaultSettings

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/10/30 下午3:16
 *
 * Description: 给打包后的apk打上渠道号的plugin
 */
class ChannelTagPlugin :Plugin<Project>{
    override fun apply(target: Project) {
        print("------------->ChannelTagPlugin   $target + ${target.javaClass} +${target.javaClass.superclass}")

    }
}
}