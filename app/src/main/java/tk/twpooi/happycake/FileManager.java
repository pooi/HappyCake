package tk.twpooi.happycake;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tw on 2016-09-23.
 */
public class FileManager {

    private Context context;

    private static final String saveDataFileName = "saveData.st";

    public FileManager(Context context){
        this.context = context;
    }

    public HashMap<String, Object> readSaveData(){

        HashMap<String, Object> map = new HashMap<>();

        ObjectInputStream input;

        try{
            input = new ObjectInputStream(new FileInputStream(new File(new File(context.getFilesDir(), "") + File.separator + saveDataFileName)));
            map = (HashMap<String, Object>)input.readObject();
            input.close();
        }catch(FileNotFoundException e){
            return null;
        }catch(IOException e){
            return null;
        }catch(Exception e){
            return null;
        }

        return map;

    }

    public boolean writeSaveData(HashMap<String, Object> map){

        ObjectOutput out = null;

        boolean check = false;

        try{
            out = new ObjectOutputStream(new FileOutputStream(new File(context.getFilesDir(),"")+File.separator+saveDataFileName));
            out.writeObject(map);
            out.close();
            check = true;
        }catch(FileNotFoundException e){
        }catch (IOException e){
        }

        return check;
    }
}
