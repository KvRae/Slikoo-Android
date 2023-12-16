package slikoo.kvrae.slikoo.utils

import android.content.Context
import android.graphics.Bitmap
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import java.io.File

 suspend fun compressFile(context: Context, file: File): File? {
    val compressedImageFile: File = Compressor.compress(context, file) {
        resolution(1280, 720)
        quality(60)
        format(Bitmap.CompressFormat.JPEG)
        size(1_097_152) // 1 MB
    }
    return compressedImageFile
}