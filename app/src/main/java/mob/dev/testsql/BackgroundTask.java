package mob.dev.testsql;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by oussa on 08/12/2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;

    public BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }


  protected void onPreExecute() {

    }

    protected String doInBackground(String... args) {
       // DbHelpe db = new DbHelpe(ctx);
        String text = args[1];
       // db.insertRow(text);
        return "une ligne a été ajouté avec avec succées";
    }

    protected void onPostExecute(String avoid) {
       // Toast.makeText(ctx,avoid,Toast.LENGTH_LONG).show();
    }








}
