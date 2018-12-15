package com.freexiaoyu.cloud.utils


import android.content.Context
import android.os.Environment
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by DIY on 2017/5/4. 14:44
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

class FileUtils {

    val sdpath: String

    /**
     * 获得sdcard路径
     * @return
     */
    val extPath: String
        get() {
            var path = ""
            if (hasSDCard()) {
                path = Environment.getExternalStorageDirectory().path
            }
            return path
        }

    init {
        // 得到当前外部存储设备的目彿 /SDCARD )
        val sdCard = Environment.getExternalStorageDirectory()
        sdpath = sdCard.absolutePath + "/"
        //Log.d("FileUtils", "FileUtils.SDPATH=" + SDPATH);
    }

    /**
     * 在SD卡上创建文件
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun createSDFile(fileName: String): File {
        val file = File(fileName)
        // 断点续传要放弿ͤ处，否则创建的都是新文件
        // if(!file.exists()){
        file.createNewFile()
        // }
        return file
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     * @return
     */
    fun createSDDir(dirName: String?): File {
        var dirName = dirName

        var dir: File? = null

        if (dirName == null) {
            dirName = ""
        }

        dir = File(sdpath + dirName)
        dir.mkdir()

        return dir
    }

    /**
     * 判断SD卡上的文件是否存圿
     *
     * @param fileName
     * @return
     */
    fun isFileExist(fileName: String): File? {

        val file = File(sdpath + fileName)
        return if (file.exists()) {
            file
        } else {
            null
        }

    }

    /**
     * 判断SD卡上的文件是否存圿
     *
     * @param fileName
     * @return
     */
    fun isExist(fileName: String): Boolean {

        val file = File(sdpath + fileName)
        return if (file.exists()) {
            true
        } else {
            false
        }

    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     *
     * @param path     创建目录
     * @param fileName 创建的文件名
     * @param input    输入浿
     * @return
     */
    fun writeFile2SDFromInput(path: String, fileName: String,
                              input: InputStream): File? {
        var file: File? = null
        var output: FileOutputStream? = null
        try {
            val dir = createSDDir(path)
            file = createSDFile(dir.path + "/" + fileName)
            output = FileOutputStream(file)
            val buffer = ByteArray(1024)
            do {
                val numread = input.read(buffer)
                if (numread <= 0) {
                    break
                }
                output.write(buffer, 0, numread)
            } while (true)
            input.close()
        } catch (e: Exception) {
            file = null
            e.printStackTrace()
        } finally {
            try {
                output!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return file
    }

    /**
     * 删除当前下载临时文件
     */
    fun delFile(localPath: String) {
        val myFile = File(localPath)
        if (myFile.exists()) {
            myFile.delete()
        }
    }

    /**
     * 删除文件夹所有内宿
     */
    fun deleteDirectoryandFile(file: File) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile) { // 判断是否是文仿
                file.delete() // delete()方法 你应该知避是删除的意濻
            } else if (file.isDirectory) { // 否则如果它是丿ت目录
                val files = file.listFiles() // 声明目录下所有的文件 files[];
                for (i in files.indices) { // 遍历目录下所有的文件
                    this.deleteDirectoryandFile(files[i]) // 把每个文仿用这个方法进行迭仿
                }
            }
            file.delete()
        }
    }

    /**
     * 根据URL得到输入
     *
     * @param urlStr
     * @return
     */
    fun getInputStreamFromURL(urlStr: String): InputStream? {
        var urlConn: HttpURLConnection? = null
        var inputStream: InputStream? = null
        try {
            val url = URL(urlStr)
            urlConn = url.openConnection() as HttpURLConnection
            inputStream = urlConn.inputStream

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return inputStream
    }

    /**
     * 根据URL下载文件,前提是这个文件当中的内容是文朿函数的返回急б是文本当中的内宿1.创建丿تURL对象
     * 2.通过URL对象,创建丿تHttpURLConnection对象 3.得到InputStream 4.从InputStream当中读取数据
     *
     * @param urlStr
     * @return
     */
    fun downloadTxT(urlStr: String): String {
        val sb = StringBuffer()
        var line: String? = null
        var buffer: BufferedReader? = null
        try {
            val url = URL(urlStr)
            val urlConn = url
                    .openConnection() as HttpURLConnection
            buffer = BufferedReader(InputStreamReader(
                    urlConn.inputStream))

            while (({ line = buffer.readLine();line }) != null) {
                sb.append(line)
            }

        } catch (e: Exception) {
            //Log.e("FileUtils", "FileUtils.downloadTxT|Exception", e);
        } finally {
            try {
                buffer!!.close()
            } catch (e: IOException) {
                //Log.e("FileUtils", "FileUtils.downloadTxT|IOException", e);
            }

        }
        return sb.toString()
    }

    /**
     * 判断SDCard是否存在
     * @return
     */
    fun hasSDCard(): Boolean {
        var b = false
        if (Environment.MEDIA_MOUNTED == Environment
                        .getExternalStorageState()) {
            b = true
        }
        return b
    }

    fun getPackagePath(context: Context): String {
        return context.filesDir.toString()
    }

    /**
     * 移动文件
     *
     * @param srcFileName 源文件完整路径
     * @param destDirName 目的目录完整路径
     * @return 文件移动成功返回true，否则返回false
     */
    fun moveFile(srcFileName: String, destDirName: String): Boolean {

        val srcFile = File(srcFileName)
        if (!srcFile.exists() || !srcFile.isFile)
            return false

        val destDir = File(destDirName)
        if (!destDir.exists())
            destDir.mkdirs()

        return srcFile.renameTo(File(destDirName + File.separator
                + srcFile.name))
    }

    /**
     * 根据路径获取图片名称
     *
     * @param url
     * @return
     */
    fun getFileName(url: String?): String {
        var imageName = ""
        if (url != null) {
            imageName = url.substring(url.lastIndexOf("/") + 1)
        }
        return imageName
    }

    /***
     * 读取asets文件内容
     *
     * @param context
     * @param url
     * @return
     */
    fun getAssets(context: Context, url: String): String {
        var line = ""
        var Result = ""
        var inputStream: InputStream? = null
        var inputStreamReader: InputStreamReader? = null
        var bufferedReader: BufferedReader? = null
        try {

            inputStream = context.resources.assets.open(url)
            inputStreamReader = InputStreamReader(inputStream!!, "UTF-8")
            bufferedReader = BufferedReader(inputStreamReader)
            while (({ line = bufferedReader.readLine();line }) != null) {
                Result += line
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close()
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close()
                }
                if (bufferedReader != null) {
                    bufferedReader.close()
                }
            } catch (e2: Exception) {
            }

        }
        return Result
    }



    companion object {
        private var instance: FileUtils? = null
            get() {
                if (field == null) {
                    field = FileUtils()
                }
                return field
            }

        @Synchronized
        fun get(): FileUtils {
            return instance!!
        }
    }
}
