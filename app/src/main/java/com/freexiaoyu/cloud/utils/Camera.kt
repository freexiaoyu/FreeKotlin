package com.freexiaoyu.cloud.utils


import android.app.Activity
import android.app.Fragment
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.support.v4.content.FileProvider

import java.io.File
import java.io.FileNotFoundException

/**
 * Created by DIY on 2017/5/3. 17:57
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
class Camera {

    fun decodeUriAsBitmap(context: Context, uri: Uri): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }

        return bitmap
    }

    companion object {


        fun getCameraPhoto(context: Context, requestcode: Int): String? {
            var url: String? = null
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val values = ContentValues()
            val photoUri = context.contentResolver.insert(
                    Media.EXTERNAL_CONTENT_URI, values)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            (context as Activity).startActivityForResult(intent, requestcode)
            val cr = context.getContentResolver()
            val cursor = cr.query(photoUri!!, null, null, null, null)
            cursor!!.moveToFirst()
            if (cursor != null) {
                url = cursor.getString(1)
                cursor.close()
            }
            return url
        }

        fun getCameraPhoto(context: Context, fragment: Fragment,
                           requestcode: Int): String? {
            var url: String? = null
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val values = ContentValues()
            val photoUri = context.contentResolver.insert(
                    Media.EXTERNAL_CONTENT_URI, values)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            fragment.startActivityForResult(intent, requestcode)
            val cr = context.contentResolver
            val cursor = cr.query(photoUri!!, null, null, null, null)
            cursor!!.moveToFirst()
            if (cursor != null) {
                url = cursor.getString(1)
                cursor.close()
            }
            return url
        }


        /***
         * 调用系统剪切
         * @param uri
         * @param outputX
         * @param outputY
         * @param requestCode
         */
        fun cropImageUri(context: Context, uri: Uri, outputX: Int, outputY: Int, requestCode: Int) {
            val intent = Intent("com.android.camera.action.CROP")
            intent.setDataAndType(uri, "image/*")
            intent.putExtra("crop", "true")
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("outputX", outputX)
            intent.putExtra("outputY", outputY)
            intent.putExtra("scale", true)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            intent.putExtra("return-data", false)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            intent.putExtra("noFaceDetection", true) // no face detection
            (context as Activity).startActivityForResult(intent, requestCode)
        }


        fun cropImageUri(context: Context, fragment: android.support.v4.app.Fragment, uri: Uri, outputX: Int, outputY: Int, requestCode: Int) {
            val intent = Intent("com.android.camera.action.CROP")
            intent.setDataAndType(uri, "image/*")
            intent.putExtra("crop", "true")
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("outputX", outputX)
            intent.putExtra("outputY", outputY)
            intent.putExtra("scale", true)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            intent.putExtra("return-data", false)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            intent.putExtra("noFaceDetection", true) // no face detection
            fragment.startActivityForResult(intent, requestCode)
        }

        /***
         * 调用系统相机
         * @param context
         * @param requestcode
         */
        fun startCamera(context: Context?, requestcode: Int) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)//action is capture
            (context as Activity).startActivityForResult(intent, requestcode)
        }


        /***
         * 调用系统相机
         * @param context 上下文
         * @param filePath 指定图片路径
         * @param requestcode 返回标识
         */
        fun startCameraUrl(context: Context, filePath: String, requestcode: Int) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            val uri: Uri
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                uri = Uri.fromFile(File(filePath))
            } else {
                /**
                 * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
                 * 并且这样可以解决MIUI系统上拍照返回size为0的情况
                 */
                uri = FileProvider.getUriForFile(context, context.packageName + ".provider", File(filePath))
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            (context as Activity).startActivityForResult(intent, requestcode)
        }

        /***
         * 调用系统相机
         * @param fragment 上下文
         * @param filePath 指定图片路径
         * @param requestcode 返回标识
         */
        fun startCameraUrl(context: Context, fragment: android.support.v4.app.Fragment, filePath: String, requestcode: Int) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            val uri: Uri
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                uri = Uri.fromFile(File(filePath))
            } else {
                /**
                 * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
                 * 并且这样可以解决MIUI系统上拍照返回size为0的情况
                 */
                uri = FileProvider.getUriForFile(context, context.packageName + ".provider", File(filePath))
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            fragment.startActivityForResult(intent, requestcode)
        }

        /***
         * 调用系统相册
         * @param context
         * @param requestcode
         */
        fun startPhotoAlbum(context: Context, requestcode: Int) {
            val intent: Intent
            if (Build.VERSION.SDK_INT < 19) {
                intent = Intent(Intent.ACTION_GET_CONTENT, null)
                intent.type = "image/*"
            } else {
                intent = Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI)
            }
            (context as Activity).startActivityForResult(intent, requestcode)
        }


        /***
         * 调用系统相册
         * @param context
         * @param requestcode
         */
        fun startPhotoAlbum(context: Context, fragment: android.support.v4.app.Fragment, requestcode: Int) {
            val intent: Intent
            if (Build.VERSION.SDK_INT < 19) {
                intent = Intent(Intent.ACTION_GET_CONTENT, null)
                intent.type = "image/*"
            } else {
                intent = Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI)
            }
            fragment.startActivityForResult(intent, requestcode)
        }

        /***
         * 调用系统相册并裁剪
         * @param context
         * @param outputX
         * @param outputY
         * @param requestCode
         */
        fun startPhotoCrop(context: Context, outputX: Int, outputY: Int, requestCode: Int) {
            val intent: Intent
            if (Build.VERSION.SDK_INT < 19) {
                intent = Intent(Intent.ACTION_GET_CONTENT, null)
                intent.type = "image/*"
            } else {
                intent = Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI)
            }
            intent.putExtra("crop", "true")
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("outputX", outputX)
            intent.putExtra("outputY", outputY)
            intent.putExtra("scale", true)
            intent.putExtra("return-data", true)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            intent.putExtra("noFaceDetection", true) // no face detection
            (context as Activity).startActivityForResult(intent, requestCode)
        }

        fun startPhotoZoom(context: Context, uri: Uri, imagePath: String, size: Int, requestCode: Int) {
            val intent = Intent("com.android.camera.action.CROP")
            intent.setDataAndType(uri, "image/*")
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop", "true")
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            // outputX,outputY 是剪裁图片的宽高
            intent.putExtra("outputX", size)
            intent.putExtra("outputY", size)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(imagePath))
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            (context as Activity).startActivityForResult(intent, requestCode)
        }


        fun startPhotoZoom(context: Context, fragment: android.support.v4.app.Fragment, uri: Uri, imagePath: String, size: Int, requestCode: Int) {
            val intent = Intent("com.android.camera.action.CROP")
            intent.setDataAndType(uri, "image/*")
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop", "true")
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            // outputX,outputY 是剪裁图片的宽高
            intent.putExtra("outputX", size)
            intent.putExtra("outputY", size)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(imagePath))
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            fragment.startActivityForResult(intent, requestCode)
        }
    }
}