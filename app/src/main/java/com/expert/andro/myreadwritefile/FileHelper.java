package com.expert.andro.myreadwritefile;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by adul on 18/09/17.
 */

public class FileHelper {

    private static final String TAG = FileHelper.class.getSimpleName();

    public static void writeToFile(String filename, String data, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            Log.e(TAG, "writeToFile: Failed");
        }
    }

    public static String readFromFile(Context context, String filename){
        String read = "";

        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                read = stringBuilder.toString();
            }
        }catch (FileNotFoundException e){
            Log.e(TAG, "readFromFile: File Not found"+ e.toString() );
        }catch (IOException e){
            Log.e(TAG, "readFromFile: Can Not Read file"+ e.toString() );
        }

        return read;
    }
}
