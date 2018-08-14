package ec.edu.ups.unesco.giiata.adacof20.utilidades;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.provider.MediaStore;

public class Reconstruccion {
	
	
	public String getPathRealURI(Context context, Uri contentUri) {
		  Cursor cursor = null;
		  try { 
		    String[] datos = { MediaStore.Images.Media.DATA };
		    cursor = context.getContentResolver().query(contentUri,  datos, null, null, null);
		    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		    cursor.moveToFirst();
		    return cursor.getString(column_index);
		  } finally {
		    if (cursor != null) {
		      cursor.close();
		    }
		  }
	}
	public Options getOpcionesImagen(Bitmap bitmap, Options opciones){
 
		System.out.println("dimensiones   "+bitmap.getWidth()+"  "+bitmap.getHeight());
		
		if (((bitmap.getWidth()>=2000)||(bitmap.getWidth()<3000))&&((bitmap.getHeight()>=2000)||(bitmap.getHeight()<3000))) {
			opciones.inSampleSize = 10;					
			System.out.println("***  dimensiones   "+bitmap.getWidth()+"  "+bitmap.getHeight()+"  ***  "+opciones.inSampleSize);
			
		}else if (((bitmap.getWidth()>=1000)||(bitmap.getWidth()<2000))&&((bitmap.getHeight()>=1000)||(bitmap.getHeight()<2000))) {
			
			opciones.inSampleSize = 9;
			
			System.out.println("***  dimensiones   "+bitmap.getWidth()+"  "+bitmap.getHeight()+"  ***  "+opciones.inSampleSize);
			
		}else if (((bitmap.getWidth()>=500)||(bitmap.getWidth()<1000))&&((bitmap.getHeight()>=500)||(bitmap.getHeight()<1000))) {
			
			opciones.inSampleSize = 4;
			System.out.println("***  dimensiones   "+bitmap.getWidth()+"  "+bitmap.getHeight()+"  ***  "+opciones.inSampleSize);
		}else if (bitmap.getWidth()<500 && bitmap.getHeight()<500 ) {
			
			opciones.inSampleSize = 1;
			System.out.println("***  dimensiones   "+bitmap.getWidth()+"  "+bitmap.getHeight()+"  ***  "+opciones.inSampleSize);
		}
		return opciones;
	}
	
	
	public  Bitmap decodeSampledBitmapFromPath(String path, int reqWidth,
            int reqHeight) {

        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(path, options);
        return bmp;
    }
	
	
 
 

    public  int calculateInSampleSize(Options options,
            int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
             }
         }
         return inSampleSize;
        }

}
