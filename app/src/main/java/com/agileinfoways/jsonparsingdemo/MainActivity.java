package com.agileinfoways.jsonparsingdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pDialog;

     ArrayList<Contacts> contactList;

    // URL to get contacts JSON
    private static String url = "http://api.androidhive.info/contacts/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = new ArrayList<Contacts>();
        new GetContacts().execute();

    }




    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e( "Response from url: " , jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("contacts");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        Contacts contacts1 = new Contacts();

                        String id = c.getString("id");
                        contacts1.setId(id);
//                        Log.e("id =",id);
                        String name = c.getString("name");
                        contacts1.setName(name);
//                        Log.e("name =",name);
                        String email = c.getString("email");
                        contacts1.setEmail(email);
//                        Log.e("email =",email);
                        String address = c.getString("address");
                        contacts1.setAddress(address);
//                        Log.e("iaddressd =",address);
                        String gender = c.getString("gender");
                        contacts1.setGender(gender);
//                        Log.e("gender =",gender);

                        // Phone node is JSON Object
                        Phone phoneObj = new Phone();
                        JSONObject phone = c.getJSONObject("phone");

                        String mobile = phone.getString("mobile");
                        phoneObj.setMobile(mobile);
//                        Log.e("mobile =",mobile);
                        String home = phone.getString("home");
                        phoneObj.setHome(home);
//                        Log.e("home =",home);
                        String office = phone.getString("office");
                        phoneObj.setOffice(office);
//                        Log.e("office =",office);

                        ArrayList<Phone> mPhoneList = new ArrayList<>();
                        mPhoneList.add(phoneObj);

                        contacts1.setmPhoneList(mPhoneList);
                        contactList.add(contacts1);


                        // tmp hash map for single contact
//                        HashMap<String, String> contact = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        contact.put("id", id);
//                        contact.put("name", name);
//                        contact.put("email", email);
//                        contact.put("mobile", mobile);
//
//                        // adding contact to contact list
//                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
//                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

//            Log.e("contactList.size() =",contactList.size()+"");

            for (int i = 0; i < contactList.size(); i++) {

                Log.e("id =",contactList.get(i).getId());
                Log.e("name =",contactList.get(i).getName());
                for (int i1 = 0; i1 < contactList.get(i).getmPhoneList().size(); i1++) {
                   Log.e("phone home =", contactList.get(i).getmPhoneList().get(i1).getHome());
                    Log.e("phone mobile",contactList.get(i).getmPhoneList().get(i1).getMobile());
                    Log.e("phone office",contactList.get(i).getmPhoneList().get(i1).getOffice());

                }
            }
            /**
             * Updating parsed JSON data into ListView
             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    MainActivity.this, contactList,
//                    R.layout.list_item, new String[]{"name", "email",
//                    "mobile"}, new int[]{R.id.name,
//                    R.id.email, R.id.mobile});
//
//            lv.setAdapter(adapter);
        }

    }
}
