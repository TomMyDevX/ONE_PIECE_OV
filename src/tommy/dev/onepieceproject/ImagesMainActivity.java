package tommy.dev.onepieceproject;

import com.polites.android.GestureImageView;



import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ImagesMainActivity extends Activity {
	
	
	  Drawable dw=null;  String folderurl="";
		 int index=0;  int max=0;	 GestureImageView view; Context contec=this;
	private Animation animationSlideInLeft;
	private Animation animationSlideOutRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	      requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		  setContentView(R.layout.empty);
		     dialog = ProgressDialog.show(contec, "", 
		                "Loading. Please wait...", true);
		  
		  Bundle extras = getIntent().getExtras();
		 folderurl= extras.getString("data");
		 //Log.e("Folder",folderurl);
		 String size=extras.getString("size");
		// Log.e("size",size);
		  max=Integer.parseInt(size);
		  getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.ds);
		  animationSlideInLeft = AnimationUtils.loadAnimation(this,R.anim.r2l);
   		animationSlideOutRight = AnimationUtils.loadAnimation(this,R.anim.l2r);
   		animationSlideInLeft.setDuration(300);
   		animationSlideInLeft.setFillBefore(false);
   		animationSlideOutRight.setDuration(300);
	     //   LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT); 
	        //imageLoader.DisplayImage("http://opvideosite.neezyl.com/data/manga/TH/693sp/p1.jpg", view);
	      

	        
	       
	        view = new GestureImageView(contec);
	        new Thread(rdimg).start();
	       // DrawableManager DrawableManager=new DrawableManager();
	       /// view.setImageDrawable(DrawableManager.fetchDrawable(folderurl+"p"+(index+1)+".jpg"));
	       // view.setLayoutParams(params);
	       // ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
	      //  layout.addView(view);

		  
		  
		  Button btprev =(Button) findViewById(R.id.prev);
		  Button btnext =(Button) findViewById(R.id.next);		
		 
		 
			 
			
		  btprev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 new Thread(rdimg).start();
				
				
			}
		});
		  
		  btnext.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View arg0) {

				 new Thread(rdimg1).start();
			}
		});
		  
		 
	}


	Runnable rdimg=new Runnable() {
		
		@Override
		public void run() {
			hdimg.post(new Runnable() {
				@Override
				public void run() {
					Message msg=new Message();
					msg.what=1;
					hdimg.sendMessage(msg);
					
				}
			});
	        DrawableManager DrawableManager=new DrawableManager();
	        dw= DrawableManager.fetchDrawable(folderurl+"p"+(index+1)+".jpg");
			hdimg.post(new Runnable() {
				@Override
				public void run() {
					Message msg=new Message();
					msg.what=200;
					hdimg.sendMessage(msg);
					
				}
			});
		}
	};
	ProgressDialog dialog;
     Handler hdimg=new Handler(){
    @Override
    public void handleMessage(Message msg) {

    	if(msg.what==200){
    	
			if(index>=0){
				if((index-1)<0){
					index=0;
				}else{
					if(index==max-1){
						index--;
					}else{
						index--;
					}
					
				}
				 TextView tv =(TextView) findViewById(R.id.current);	
				 tv.setText((index+1)+"/"+max);
				setContentView(R.layout.empty);
		        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		        view = new GestureImageView(contec);
		        view.startAnimation(animationSlideOutRight);
		        view.setImageDrawable(dw);
		        view.setLayoutParams(params);

		        ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
		        layout.addView(view);
			

			}
			dialog.dismiss();
    	}else{
    		dialog.show();
    	}
    	
    }};
    
	Runnable rdimg1=new Runnable() {
		
		@Override
		public void run() {
			hdimg1.post(new Runnable() {
				@Override
				public void run() {
					Message msg=new Message();
					msg.what=1;
					hdimg1.sendMessage(msg);
					
				}
			});
	        DrawableManager DrawableManager=new DrawableManager();
	        dw= DrawableManager.fetchDrawable(folderurl+"p"+(index+1)+".jpg");
			hdimg1.post(new Runnable() {
				@Override
				public void run() {
					Message msg=new Message();
					msg.what=200;
					hdimg1.sendMessage(msg);
					
				}
			});
		}
	};
     Handler hdimg1=new Handler(){
    @Override
    public void handleMessage(Message msg) {
   
    	if(msg.what==200){
    		
			if(index<=max-1){
				 TextView tv =(TextView) findViewById(R.id.current);	
				 tv.setText((index+1)+"/"+max);
				setContentView(R.layout.empty);
		        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		        view = new GestureImageView(contec);
		        view.startAnimation(animationSlideInLeft);
		        DrawableManager DrawableManager=new DrawableManager();
		      //  view.setImageDrawable(DrawableManager.fetchDrawableOnThread((folderurl+"p"+(index+1)+".jpg"),view);
		       // DrawableManager.fetchDrawableOnThread((folderurl+"p"+(index+1)+".jpg"),view);
		        view.setImageDrawable(dw);
		        view.setLayoutParams(params);
		       
		        ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
		        layout.addView(view);
			
				if((index+1)>max-1){
					index=max-1;
				}else{
					index++;
				}
			}
			dialog.dismiss();
    	}else{
    		dialog.show();
    	}
    }};
    
    
}
