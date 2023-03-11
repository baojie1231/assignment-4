package mg.x261.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class json {

    private TextView display;



    public void btnLoad(View view) {
        String url = "https://studio.mg/student/api-music-lyrics/index.php?search=demo";
        new GetDataOnline().execute(url);

    }


    private class GetDataOnline extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();
            String url = strings[0];
            String jsonStr = sh.requestOnlineData(url);

            Log.e("TAG", "Response: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting the songCount node
                    String str = jsonObj.getJSONObject("result").getString("songCount");
                    return str;
                } catch (final JSONException e) {
                    Log.e("TAG", "Json parsing error: " + e.getMessage());
                    ;
                    return null;

                }

            } else {
                Log.e("TAG", "Couldn't get json from server.");
                ;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //TODO:  Update the UI here
            if (result != null) {
                display.setText(new StringBuilder().append("Data :").append(result).toString());
            }else{
                display.setText(" --- return from json is null -- ");
            }
        }
    }
}
