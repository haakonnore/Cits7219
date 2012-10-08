package au.edu.uwa.csp.respreport;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import au.edu.uwa.csp.respreport.R;

public class AuthUserActivity extends Activity {

	private static String SOAP_ACTION2 = "http://tempuri.org/IService1/AuthUser";
	private static String NAMESPACE = "http://tempuri.org/";
	private static String METHOD_NAME1 = "AuthUser";
	private static String URL = "http://marge.csse.uwa.edu.au/RespServ/Service1.svc?wsdl";
	Button btnConvert;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth_user);


	}

	
	class Data {
	      String namespace;
	      String method;
	 }

	public void sendMessage(View view) {
		// setContentView(textView);

		CallSOAPTask task = new CallSOAPTask();
        task.applicationContext = AuthUserActivity.this;
        task.parentActivity = this;
		task.execute();
	}
	
	public void alertDialog(String msg)
	{
		AlertDialog.Builder altDialog= new AlertDialog.Builder(this);
    	altDialog.setMessage(msg); // here add your message
    	altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int which) {
    			// TODO Auto-generated method stub
    			
    		}});
			
    	altDialog.show();
	
	}
	
	
	
	class CallSOAPTask extends AsyncTask<String, Void, String> {
		 private ProgressDialog dialog;
		 
		         protected Context applicationContext;
		 
		         protected AuthUserActivity parentActivity;
		  
		 
		         @Override
		 
		         protected void onPreExecute() {
		 
		            //this.dialog = ProgressDialog.show(applicationContext, "Calling", "Auth User...", true);
		 
		         }
		 
		         @Override
		         protected void onPostExecute(final String result) {

//		        	 this.dialog.dismiss();

		        	AuthUserActivity.this.runOnUiThread(new Runnable() {
						public void run() {
							parentActivity.alertDialog("Return from call: " + result);
						   }
						});
		                 
		 
		         }

		  


		
		@Override
		 protected String doInBackground(String... params) {
		    //make your network call and return SoapObject
			// Initialize soap request + add parameters
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);

		 		// Use this to add parameters
				request.addProperty("userName","dglance");
				request.addProperty("password", "password");

				// Declare the version of the SOAP request
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);

				envelope.setOutputSoapObject(request);
				envelope.dotNet = true;

				try {
					HttpTransportSE androidHttpTransport = new HttpTransportSE(
							URL);

					// this is the actual part that will call the webservice
					androidHttpTransport.call(SOAP_ACTION2, envelope);

					// Get the SoapResult from the envelope body.
					final SoapObject result = (SoapObject) envelope.bodyIn;

					if (result != null) {
						// Get the first property and change the label text
						
						return result.getProperty(0).toString();
						
						
					} else {
						Toast.makeText(getApplicationContext(), "No Response",
								Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
				
			
		 }

		}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_auth_user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
