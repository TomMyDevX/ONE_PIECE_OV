package tommy.dev.onepieceproject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.Loader;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  implements TextWatcher{

	final 				MainActivity 		context=this;
	private  static  	ListView 			list;
	private				LazyAdapter 		adapter;
	private				String 				URLGOBAL="";
	private Button button;
	 Thread myThread=new Thread();
	 CheckBox updown_silde;
     CheckBox en;
    CheckBox es;
    CheckBox th;
    CheckBox ge;
    CheckBox fr;
    AutoCompleteTextView autoep;
     ImageView up;
	 ImageView down;
	 ImageView onplay;
	 ImageView 	im;
	 ImageView im_lang;
	  LinearLayout slidela;
	  TextView modestatus;
	  TextView loading;
	  AutoCompleteTextView myAutoComplete	;
	  @Override
	  protected void onResume()
	  {
	      super.onResume();

	      
	      myAutoComplete.clearFocus();
	      slidela.requestFocus();
	  }
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	if(isTabletDevice()){
    		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);   
    	}else{
    		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);   
    	}
    	
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

       
        
        SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
        if(settings1.getInt("ads_option", 0)==0){
	

			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.dialog_show);
			dialog.setTitle("Welcome!");
 
			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.text);
			text.setText("Sorry. My server is down.I moving file to second Server. Thank a lot!");
			
 
			Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				    SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
				    SharedPreferences.Editor editor1 = settings1.edit();
				    editor1.putInt("ads_option", 1);
				    editor1.commit();
					dialog.dismiss();
				}
			});
 
			dialog.show();
		  
}
        
       // isAvailable();
   
     
 
       
        en			=(CheckBox) 	findViewById(R.id.en);
        es			=(CheckBox) 	findViewById(R.id.es);
        th			=(CheckBox) 	findViewById(R.id.th);
        ge			=(CheckBox) 	findViewById(R.id.ge);
        fr			=(CheckBox) 	findViewById(R.id.fr);
        autoep			=(AutoCompleteTextView) 	findViewById(R.id.go_src);
        autoep.clearFocus();
        up=(ImageView) findViewById(R.id.up_bt);
		down=(ImageView) findViewById(R.id.down_bt);
		onplay=(ImageView) findViewById(R.id.onplay);
		updown_silde=(CheckBox) findViewById(R.id.updown_silde);
        im					=(ImageView) 	findViewById(R.id.ImageView1);
        list				=(ListView)		findViewById(R.id.list);
        im_lang	=(ImageView)findViewById(R.id.icon_img);
	    slidela=(LinearLayout) findViewById(R.id.dfdsf);
	    modestatus=(TextView) 		findViewById(R.id.modestatus);
	    loading = (TextView) findViewById(R.id.loading);
	    myAutoComplete			=(AutoCompleteTextView) 	findViewById(R.id.go_src);
	    
        im.setImageResource(R.drawable.i7);
	      myAutoComplete.clearFocus();
	      slidela.requestFocus();
	      
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);inputMethodManager.showSoftInput(autoep, InputMethodManager.SHOW_IMPLICIT);
        make_slide();

		 URLGOBAL=getDefaultUrlLang();
		 if(myThread.isAlive()){
			    myThread.interrupt(); 
			}
			myThread = new Thread(LoderUrl);
			myThread.start();
		// new Thread(LoderUrl).start();


		 
		 
        en.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {

				 setdefaultUserLang(0);
				 URLGOBAL=getDefaultUrlLang();
				 if(myThread.isAlive()){
					    myThread.interrupt(); 
					}
					myThread = new Thread(LoderUrl);
					myThread.start();
					
	
				
			}
		});
        ge.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
				setdefaultUserLang(1);
				 URLGOBAL=getDefaultUrlLang();
				 
				 if(myThread.isAlive()){
					    myThread.interrupt(); 
					}
					myThread = new Thread(LoderUrl);
					myThread.start();
				
	
				//}
				
			}
		});
        
        th.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
	
				setdefaultUserLang(2);
				 URLGOBAL=getDefaultUrlLang();
				 
				 if(myThread.isAlive()){
					    myThread.interrupt(); 
					}
					myThread = new Thread(LoderUrl);
					myThread.start();

			
			}
		});
        es.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setdefaultUserLang(3);
				 URLGOBAL=getDefaultUrlLang();
				 
				 if(myThread.isAlive()){
					    myThread.interrupt(); 
					}
					myThread = new Thread(LoderUrl);
					myThread.start();

				
				//}
				
			}
		});
        fr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setdefaultUserLang(4);
				 URLGOBAL=getDefaultUrlLang();
				 
				 if(myThread.isAlive()){
					    myThread.interrupt(); 
					}
					myThread = new Thread(LoderUrl);
					myThread.start();

				
				//}
				
			}
		});
      final CheckBox cb_menu=(CheckBox) findViewById(R.id.bt_img_menu);
		 registerForContextMenu(cb_menu);
         cb_menu.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		//Log.e("","setting click");
		 openContextMenu(v);
			
		}
	});
			up.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
		             
					  adapter=new LazyAdapter(context, todoItemsmapGobal);
					  list.setAdapter(adapter);
					  list.setSelection(0);
					  adapter.notifyDataSetChanged();
				}
			});
			down.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					  adapter=new LazyAdapter(context, todoItemsmapGobal);
					  list.setAdapter(adapter);
					  list.setSelection(todoItemsmapGobal.size()-1);
					  adapter.notifyDataSetChanged();
				}
			});
			onplay.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
		          
		             String backgroundImageName = (String) im_lang.getTag();
		             TextView txt_lang	=(TextView)findViewById(R.id.modestatus);
		        	 SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
					  adapter=new LazyAdapter(context, todoItemsmapGobal);
					  list.setAdapter(adapter);
					  list.setSelection(settings1.getInt(txt_lang.getText()+backgroundImageName, 0)); 
					  adapter.notifyDataSetChanged();
				}
			});
			autoep.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					 TranslateAnimation slide1 = new TranslateAnimation(0, -50, 0,0 );   
	                    slide1.setDuration(500);   
	                    slide1.setFillBefore(true);
	                    slide1.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animation arg0) {
								up.setVisibility(View.VISIBLE);
								down.setVisibility(View.VISIBLE);
								onplay.setVisibility(View.VISIBLE);
							}
						});
	                    TranslateAnimation slide = new TranslateAnimation(-100, 0, 0,0 );   
	                    slide.setDuration(500);   
	                    slidela.setAnimation(slide1);
	                    autoep.setText("");
	                    
				}
			});
			autoep.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View arg0, boolean arg1) {
						updown_silde.setButtonDrawable(R.drawable.navigate_left);
			    	 	updown_silde.setChecked(true);
			    	 	make_slide_with_noaction();
	                    autoep.setText("");
				}
			});
			autoep.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
		             im_lang	=(ImageView)findViewById(R.id.icon_img);
					  adapter=new LazyAdapter(context, todoItemsmapGobal);
					  list.setAdapter(adapter);
					  String s = ((TextView) arg1).getText().toString();
					  int index=-1;
					  for(int i=0;i<todoItemsmapGobal.size();i++){
						  if(todoItemsmapGobal.get(i).get("title").startsWith(s)){
							  index=i;
						  }
					  }
				     list.setSelection(index);
	  			  
				     adapter.notifyDataSetChanged();
				     onBackPressed();
				}
			});

    }
    
    //////////////////////////////////////////////////////////DCTECT HOME AND BACK PREASSS
    @Override
    public void onBackPressed() {
    	 onResume();
    	 updown_silde.setChecked(false);
    	 updown_silde.setButtonDrawable(R.drawable.navigate_right);
    	 make_slide_with_noaction();
    	 autoep			=(AutoCompleteTextView) 	findViewById(R.id.go_src);
    	 autoep.setText("");
    	// autoep.setFocusable(false);
    }


    
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onDestroy()
    {
        list.setAdapter(null);
        super.onDestroy();
    }
    
    public OnClickListener listener=new OnClickListener(){
        @Override
        public void onClick(View arg0) {
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
        }
    };

	////////////////////////////CONTECT MENU////////////////////////////////////////////////////////////////////////////////////////

	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderIcon(R.drawable.system_config_boot);
            menu.setHeaderTitle("App Setting.");
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
    }
	@Override
	 public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menumoviemode:onCreateDialog(MODE_DIALOG_MOVIE);break;
		case R.id.report:onCreateDialog(MODE_DIALOG_REPORT);break;
		case R.id.exit:onCreateDialog(MODE_APP_EXIT);break;
		}		
		
		return super.onOptionsItemSelected(item);
	}
	
	public int getdefaultMovie(){
    SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
    return settings1.getInt("movie_option", 0);
}
	public boolean setdefaultMovie(int arg1){
	    SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
	    SharedPreferences.Editor editor1 = settings1.edit();
	    editor1.putInt("movie_option", arg1);
	    editor1.commit();
	    return editor1.commit();
	}
	
	public int getdefaultUserLang(){
	    SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
	    return settings1.getInt("defaultlang", 0);
	}
		public boolean setdefaultUserLang(int arg1){
		    SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
		    SharedPreferences.Editor editor1 = settings1.edit();
		    editor1.putInt("defaultlang", arg1);
		    editor1.commit();
		    return editor1.commit();
		}
	
	
	int MODE_DIALOG_MOVIE=0;
	int MODE_DIALOG_REPORT=1;	int MODE_DIALOG_MANGA=2;int MODE_APP_EXIT=3;
	
	
	protected Dialog onCreateDialog(int id) {
		  final Dialog dialog;
		  switch(id) {
		   case 0:
		    dialog = new Dialog(this);
		    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    dialog.setContentView(R.layout.moviemode);
		    dialog.setTitle("Movie Mode");	
		    RadioButton rbdefaultmodeonepiece=(RadioButton) dialog.findViewById(R.id.modeonepiece);
		    RadioButton rbdefaultmodemovie=(RadioButton) dialog.findViewById(R.id.modemovie);
		    RadioButton rbdefaultmodemanga=(RadioButton) dialog.findViewById(R.id.modemanga);
		    if(getdefaultMovie()==0){
		    	rbdefaultmodeonepiece.setChecked(true);
		    }else if(getdefaultMovie()==1){
		    	rbdefaultmodemovie.setChecked(true);
		    }else if(getdefaultMovie()==2){
		    	rbdefaultmodemanga.setChecked(true);
		    }
		    final ImageView im_lang=(ImageView) findViewById(R.id.icon_img);
		    rbdefaultmodeonepiece.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {

					setdefaultMovie(0);
					 URLGOBAL=getDefaultUrlLang();
					 if(myThread.isAlive()){
						    myThread.interrupt(); 
						}
						myThread = new Thread(LoderUrl);
						myThread.start();
						dialog.dismiss();
								
				}
			});
		    rbdefaultmodemovie.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					setdefaultMovie(1);
					 	URLGOBAL=getDefaultUrlLang();
						
						 
						 if(myThread.isAlive()){
							    myThread.interrupt(); 
							}
							myThread = new Thread(LoderUrl);
							myThread.start();
						    dialog.dismiss();
				}
			});
		    rbdefaultmodemanga.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					setdefaultMovie(2);
			        URLGOBAL=getDefaultUrlLang();
		
					 if(myThread.isAlive()){
						    myThread.interrupt(); 
						}
						myThread = new Thread(LoderUrl);
						myThread.start();
						dialog.dismiss();
				}
			});
		    dialog.show();
		    break;
		    
		   case 1: dialog = new Dialog(this);
		    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    dialog.setContentView(R.layout.report);
		    dialog.setTitle("Report");	
		    ImageView bt_img_report=(ImageView) dialog.findViewById(R.id.bt_img_report);
		    final EditText title=(EditText) dialog.findViewById(R.id.reporttitle);
		    final EditText message=(EditText) dialog.findViewById(R.id.reportmess);

		    bt_img_report.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				
						
				
					 if(myThread.isAlive()){
						    myThread.interrupt(); 
						}
					
						
							
						myThread = new Thread(new Runnable() {
						Handler Handlerx=   new Handler(){
							public void handleMessage(Message msg) {
								if(msg.what==200){
									dialog.dismiss();
									Toast.makeText(context, "Thank you for report. We will fix this soon.", Toast.LENGTH_LONG).show();
					
								}else if(msg.what==500){
									Toast.makeText(context, "Field rquest.", Toast.LENGTH_LONG).show();
								}
							
							}
							};
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
				
					if(!title.getText().toString().matches("")&&!message.getText().toString().matches("")){
		
		
					Handlerx.post(new Runnable() {
						
						@Override
						public void run() {
							
							Message msg=new Message();
							msg.what=200;
						    Handlerx.sendMessage(msg);
							
						}
					});
					Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_SUBJECT, "One Piece Report");
					intent.putExtra(Intent.EXTRA_TEXT, message.getText());
					intent.setData(Uri.parse("mailto:maxzateam@gmail.com")); // or just "mailto:" for blank
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
					startActivity(intent);
				  
			}else{
				Handlerx.post(new Runnable() {
					
					@Override
					public void run() {
						Message msg=new Message();
						msg.what=500;
					    Handlerx.sendMessage(msg);
						
					}
				});
				}
				
						}
					});
						myThread.start();
					
				}
			}); dialog.show();
		    break;
		   case 3:finish();break;
		    
		default:
		    dialog = null;
		}
		return null;
		}
	

	 public void makeloading(final boolean show){
		  final Handler handler = new Handler();
		    new Thread(new Runnable() {
		    	TextView loading = (TextView) findViewById(R.id.loading);
		        @Override
		        public void run() {
		         
		        	if(show){
		        		
		            handler.post(new Runnable() {
		            	
		                @Override
		                    public void run() {
		                	
		                    TranslateAnimation slide1 = new TranslateAnimation(0, 2000, 0,0 );   
		                    slide1.setDuration(500);   
		                    slide1.setFillBefore(true);
		                	
		                   
		                    TranslateAnimation slide = new TranslateAnimation(2000, 0, 0,0 );   
		                    slide.setDuration(500);   
		                    
		                    loading.setText("Loading..");
		                    loading.startAnimation(slide); 
		                	loading.setVisibility(View.VISIBLE);
		                	list.setAnimation(slide1);
		                	list.setVisibility(View.INVISIBLE);
		                    
		                }});}else{
		                	
				            handler.post(new Runnable() {
				                @Override
				                    public void run() {
				                    TranslateAnimation slide1 = new TranslateAnimation(0,2000, 0,0 );   
				                    loading.setText("Complete!");
				                    slide1.setDuration(1000);   
				                    slide1.setFillBefore(true);   
				                    loading.startAnimation(slide1); 
				                    
				                    slide1.setAnimationListener(new AnimationListener() {
										
										@Override
										public void onAnimationStart(Animation animation) {
						                    TranslateAnimation slide = new TranslateAnimation(2000, 0, 0,0 );   
						                    slide.setDuration(1000);   
						                	list.setAnimation(slide);
						                	list.setVisibility(View.VISIBLE);
										}
										
										@Override
										public void onAnimationRepeat(Animation animation) {
									
											
										}
										
										@Override
										public void onAnimationEnd(Animation animation) {
											loading.setVisibility(View.GONE);

										}
									});
				                	
				                }});
				            
				            
		                }
		            }
		        }).start();
	 }

	  ArrayList<HashMap<String, String>> todoItemsmapGobal = new ArrayList<HashMap<String, String>>();
	  Handler urlloader=new Handler(){
		  @Override
		public void handleMessage(Message msg) {
			  	if(msg.what==200){
						  adapter=new LazyAdapter(context, todoItemsmapGobal);
						  list.setAdapter(adapter);
						  
				            ImageView im_lang	=(ImageView)findViewById(R.id.icon_img);
				            final String backgroundImageName = (String) im_lang.getTag();
				            
				            final TextView txt_lang	=(TextView)findViewById(R.id.modestatus);

				        	 SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
							 
						  
						  list.setSelection(settings1.getInt(txt_lang.getText()+backgroundImageName, 0));
			  			  
						  adapter.notifyDataSetChanged();
						   
					       myAutoComplete.addTextChangedListener(context);
					       String []title=new String[todoItemsmapGobal.size()];
					      for(int i=0;i<todoItemsmapGobal.size();i++){
					    	  title[i]=todoItemsmapGobal.get(i).get("title");
					      }
					      myAutoComplete.setText("");
					       myAutoComplete.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, title));
			  	}
			  	if(msg.what==0){
			  			makeloading(true);
			  		}
			  	if(msg.what==1){
			  		makeloading(false);
			  	}
	  }};
	  Runnable LoderUrl=new Runnable() {
		
		@Override
		public void run() {
			urlloader.post(new Runnable() {
				
				@Override
				public void run() {
					Message msg=new Message();
					msg.what=0;
				    urlloader.sendMessage(msg);
					
				}
			});
	        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://assholy.3owl.com/data/checkloading.xml");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(5000);
                    urlc.connect();
            if (urlc.getResponseCode() == 200) {
            	
    	       isOnline.post(CONNECT_COMPLETE);
          
			final ArrayList<HashMap<String, String>> todoItemsmap = new ArrayList<HashMap<String, String>>();
			XmlPullParser todolistXml = null;
			try {
				todolistXml = XmlPullParserFactory.newInstance().newPullParser();
				try {
					URL URL	=new URL(URLGOBAL);
					URLConnection ucon = URL.openConnection();
					ucon.setUseCaches(false);
					ucon.setRequestProperty("Cache-Control", "no-cache");
					todolistXml.setInput(ucon.getInputStream(),null);
				
				} catch (MalformedURLException e) {
					isOnline.post(CONNECT_ERROR);
				} catch (IOException e) {
					isOnline.post(CONNECT_ERROR);
				}
			} catch (XmlPullParserException e1) {
				isOnline.post(CONNECT_ERROR);
			}

			//XmlResourceParser todolistXml = getResources().getXml(R.xml.data);

			int eventType = -1;
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				if (eventType == XmlResourceParser.START_TAG) {

					String strNode = todolistXml.getName();
					if (strNode.equals("url")) {
						HashMap<String, String> map=new HashMap<String, String>();
						map.put("title", todolistXml.getAttributeValue(null, "title"));
					//	Log.e("",todolistXml.getAttributeValue(null, "title"));
						map.put("data", todolistXml.getAttributeValue(null, "data"));
						map.put("size", todolistXml.getAttributeValue(null, "size"));
						map.put("thumbnail", todolistXml.getAttributeValue(null, "thumbnail"));
						todoItemsmap.add(map);
					
					}
				}

				try {
					eventType = todolistXml.next();
				} catch (XmlPullParserException e) {
					isOnline.post(CONNECT_ERROR);
				} catch (IOException e) {
					isOnline.post(CONNECT_ERROR);
				}
				
			}
			urlloader.post(new Runnable() {
				
				@Override
				public void run() {
					todoItemsmapGobal=todoItemsmap;
					Message msg=new Message();
					msg.what=200;
				    urlloader.sendMessage(msg);
					
				}
			});
			urlloader.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					todoItemsmapGobal=todoItemsmap;
					Message msg=new Message();
					msg.what=1;
				    urlloader.sendMessage(msg);
					
				}
			},300); 
		
            }else{
	         	   isOnline.post(CONNECT_ERROR);
	            }
	            } catch (MalformedURLException e1) {
	         	   isOnline.post(CONNECT_ERROR);
	        } catch (IOException e) {
	     	   	   isOnline.post(CONNECT_ERROR);
	            }
	            }else{
	               isOnline.post(CONNECT_ERROR);
	            }

		}
	  };
	  
	  Handler	isOnline=new Handler(){
			@Override
			public void handleMessage(Message msg) {
		
				if(msg.what==200){

				}else if(msg.what==404){

					AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
					alertbox.setMessage("Please check your connection!");
					alertbox.setCancelable(false);
					alertbox.setNeutralButton("Retry", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							
							loading.setText("No Connection.");
							arg0.dismiss();
						
						}
	            });
	            alertbox.show();
			
		}							
		
		
	}
}; 
			     Runnable   CONNECT_ERROR     =	   new Runnable() {
				@Override
				public void run() {
					Message onstatus=new Message();
					onstatus.what=404;
					isOnline.sendMessage(onstatus);
				}
				};
				 Runnable   CONNECT_COMPLETE     =	   new Runnable() {
					@Override
					public void run() {
						Message onstatus=new Message();
						onstatus.what=200;
						isOnline.sendMessage(onstatus);
					}
				};

				private boolean isTabletDevice() {  
					if (android.os.Build.VERSION.SDK_INT >= 11) { // honeycomb  
					  // test screen size, use reflection because isLayoutSizeAtLeast is only available since 11  
					  Configuration con = getResources().getConfiguration();  
					  try {  
					     Method mIsLayoutSizeAtLeast = con.getClass().getMethod("isLayoutSizeAtLeast", int.class);  
					     Boolean r = (Boolean) mIsLayoutSizeAtLeast.invoke(con, 0x00000004); // Configuration.SCREENLAYOUT_SIZE_XLARGE  
					     return r;  
					  } catch (Exception x) {  
					     //x.printStackTrace();  
					     return false;  
					  }  
					}  
					return false;  
					}  
				
				public String getDefaultUrlLang(){
					String urlxml="";
					if(getdefaultMovie()==0){
						if(getdefaultUserLang()==0){
							urlxml="http://assholy.3owl.com/data/dataen.xml";	
							 im_lang.setTag("us");
							 im_lang.setImageResource(R.drawable.us);
						}else if(getdefaultUserLang()==1){
							urlxml="http://assholy.3owl.com/data/datager.xml";	
							 im_lang.setTag("ger");
							 im_lang.setImageResource(R.drawable.ge);
						}else if(getdefaultUserLang()==2){
							urlxml="http://assholy.3owl.com/data/datath.xml";	
							 im_lang.setTag("th");
							 im_lang.setImageResource(R.drawable.flag_th2);
						}else if(getdefaultUserLang()==3){
							urlxml="http://assholy.3owl.com/data/dataes.xml";	
							 im_lang.setTag("es");
							 im_lang.setImageResource(R.drawable.flag_sp);
						}else if(getdefaultUserLang()==4){
							urlxml="http://assholy.3owl.com/data/datafr.xml";	
							 im_lang.setImageResource(R.drawable.fr);
							 im_lang.setTag("fr");
						}
						 
					}else if(getdefaultMovie()==1){
						if(getdefaultUserLang()==0){
							urlxml="http://assholy.3owl.com/data/mven.xml";	
							 im_lang.setTag("us");
							 im_lang.setImageResource(R.drawable.us);
						}else if(getdefaultUserLang()==1){
							urlxml="http://assholy.3owl.com/data/mvger.xml";	
							 im_lang.setTag("ger");
							 im_lang.setImageResource(R.drawable.ge);
						}else if(getdefaultUserLang()==2){
							urlxml="http://assholy.3owl.com/data/mvth.xml";	
							 im_lang.setTag("th");
							 im_lang.setImageResource(R.drawable.flag_th2);
						}else if(getdefaultUserLang()==3){
							urlxml="http://assholy.3owl.com/data/mves.xml";	
							 im_lang.setTag("es");
							 im_lang.setImageResource(R.drawable.flag_sp);
						}else if(getdefaultUserLang()==4){
							urlxml="http://assholy.3owl.com/data/mvfr.xml";	
							 im_lang.setTag("fr");
							 im_lang.setImageResource(R.drawable.fr);
						}
						 
					}else if(getdefaultMovie()==2){
						if(getdefaultUserLang()==0){
							 urlxml="http://assholy.3owl.com/data/manga/mangaen.xml";
							 im_lang.setTag("us");
							 im_lang.setImageResource(R.drawable.us);
						}else if(getdefaultUserLang()==1){
							 urlxml="http://assholy.3owl.com/data/manga/mangager.xml";
							 im_lang.setTag("ger");
							 im_lang.setImageResource(R.drawable.ge);
						}else if(getdefaultUserLang()==2){
							 urlxml="http://assholy.3owl.com/data/manga/mangath.xml";
							 im_lang.setTag("th");
							 im_lang.setImageResource(R.drawable.flag_th2);
						}else if(getdefaultUserLang()==3){
							 urlxml="http://assholy.3owl.com/data/manga/mangaes.xml";
							 im_lang.setTag("es");
							 im_lang.setImageResource(R.drawable.flag_sp);
						}else if(getdefaultUserLang()==4){
							 urlxml="http://assholy.3owl.com/data/manga/mangafr.xml";
							 im_lang.setTag("fr");
							 im_lang.setImageResource(R.drawable.fr);
						}
				
							
					}
					setdefaultLabelUserMovie();
					return urlxml;
				}
				public void setdefaultLabelUserMovie(){
					if(getdefaultMovie()==0){
						modestatus.setText("One Piece");
					}else if(getdefaultMovie()==1){
						modestatus.setText("The Movie");
					}else if(getdefaultMovie()==2){
						modestatus.setText("Manga");
					}

					
					
				}
				
				public void make_slide_with_noaction(){
	
					
					
					
					up.setVisibility(View.GONE);
					down.setVisibility(View.GONE);
					down.setVisibility(View.GONE);
					onplay.setVisibility(View.GONE);
					
							
		                    TranslateAnimation slide1 = new TranslateAnimation(0, -50, 0,0 );   
		                    slide1.setDuration(500);   
		                    slide1.setFillBefore(true);
		                    slide1.setAnimationListener(new AnimationListener() {
								
								@Override
								public void onAnimationStart(Animation animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationRepeat(Animation animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationEnd(Animation arg0) {
									up.setVisibility(View.VISIBLE);
									down.setVisibility(View.VISIBLE);
									onplay.setVisibility(View.VISIBLE);
								}
							});
		                   
		                    TranslateAnimation slide = new TranslateAnimation(-100, 0, 0,0 );   
		                    slide.setDuration(500);   
		                    

		                    
							if(updown_silde.isChecked()){
								
								slidela.setAnimation(slide1);
								updown_silde.setButtonDrawable(R.drawable.navigate_right);

							}else{
								
								slidela.setAnimation(slide);
								updown_silde.setButtonDrawable(R.drawable.navigate_left);
								up.setVisibility(View.GONE);
								down.setVisibility(View.GONE);
								onplay.setVisibility(View.GONE);
							}
							
				
					
					
					
					
					
				}
public void make_slide(){
	
					
					
					
					up.setVisibility(View.GONE);
					down.setVisibility(View.GONE);
					down.setVisibility(View.GONE);
					onplay.setVisibility(View.GONE);
					updown_silde.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							
		                    TranslateAnimation slide1 = new TranslateAnimation(0, -50, 0,0 );   
		                    slide1.setDuration(500);   
		                    slide1.setFillBefore(true);
		                    slide1.setAnimationListener(new AnimationListener() {
								
								@Override
								public void onAnimationStart(Animation animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationRepeat(Animation animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationEnd(Animation arg0) {
									up.setVisibility(View.VISIBLE);
									down.setVisibility(View.VISIBLE);
									onplay.setVisibility(View.VISIBLE);
								}
							});
		                   
		                    TranslateAnimation slide = new TranslateAnimation(-100, 0, 0,0 );   
		                    slide.setDuration(500);   
		                    

		                    
							if(updown_silde.isChecked()){
								
								slidela.setAnimation(slide1);
								updown_silde.setButtonDrawable(R.drawable.navigate_right);
							}else{
								slidela.setAnimation(slide);
								up.setVisibility(View.GONE);
								down.setVisibility(View.GONE);
								onplay.setVisibility(View.GONE);
								updown_silde.setButtonDrawable(R.drawable.navigate_left);
							}
							
						}
					});
					
					
					
					
					
				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}
}




