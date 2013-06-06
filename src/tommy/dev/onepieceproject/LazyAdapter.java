package tommy.dev.onepieceproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LazyAdapter extends BaseAdapter {
	private static final int TYPE_COUNT = 2;
	private static final int TYPE_ITEM_COLORED = 1;
	private static final int TYPE_ITEM_NORMAL = 0;
    private Activity activity;
    private int data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    ArrayList<HashMap<String, String>> todoItemsmap = new ArrayList<HashMap<String, String>>();;
    public LazyAdapter(Activity a,ArrayList<HashMap<String, String>> list) {
    	todoItemsmap=list;
        activity = a;
        data=todoItemsmap.size();
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    
    
    
    
    public int getCount() {
        return data;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }
   

    public View getView(final int position, View convertView, final ViewGroup parent) {
    	
        View vi=convertView;
        if(convertView==null)vi = inflater.inflate(R.layout.item, null);
        if(position<todoItemsmap.size()){
        	

            ImageView im_lang	=(ImageView)activity.findViewById(R.id.icon_img);
            final String backgroundImageName = (String) im_lang.getTag();
            
            final TextView txt_lang	=(TextView)activity.findViewById(R.id.modestatus);

        	 SharedPreferences settings1 = activity.getSharedPreferences("One_Piece_Video_By_TomMy", 0);
			 

        TextView text=(TextView)vi.findViewById(R.id.text);;
        text.setTextColor(Color.DKGRAY);
       // text.setBackgroundColor(0xFFB8E459);
        ImageView image=(ImageView)vi.findViewById(R.id.image);
        image.setImageResource(R.drawable.ficon);
        //Log.e("POS",""+position);

        
        text.setText(todoItemsmap.get(position).get("title"));
        vi.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View arg0) {
				setSelectedItem(position);
                notifyDataSetChanged();     
                
               
                
                
			    SharedPreferences settings1 = activity.getSharedPreferences("One_Piece_Video_By_TomMy", 0);
			    SharedPreferences.Editor editor1 = settings1.edit();
			    editor1.putInt(txt_lang.getText()+backgroundImageName, position);
			    editor1.commit();
                Log.e("Save",txt_lang.getText()+backgroundImageName+"->"+position);
				//layout.setBackgroundResource(R.drawable.app_background_d);
				//Log.e("POS",""+position+"|"+todoItemsmap.get(position).get("data"));
		       // String filename =todoItemsmap.get(position).get("data");
		        //String filenameArray[] = filename.split("\\.");
		        //String extension = filenameArray[filenameArray.length-1];
		      //  Log.e("",filename);
				if(todoItemsmap.get(position).get("data").equals("")){
		
				}else{
					if(getdefaultMovie()!=2){
								Intent tostart = new Intent();
								tostart.setAction(Intent.ACTION_VIEW);
								tostart.setDataAndType(Uri.parse(todoItemsmap.get(position).get("data")), "video/*");
								List<ResolveInfo> resInfo = activity.getPackageManager().queryIntentActivities(tostart, 0);
								ResolveInfo best = null;    
								for (final ResolveInfo info : resInfo){
									
									if (info.activityInfo.name.toLowerCase().contains("mxtech")){
										Log.e("",info.activityInfo.name.toLowerCase());
										 best = info;
									}
				                       
								}
					               if (best != null) 
					                {
					                    tostart.setClassName(best.activityInfo.packageName, best.activityInfo.name); 
					                    activity.startActivity(tostart);
					                }else{
									      try {
									      		activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.mxtech.videoplayer.ad")));
									      	} catch (android.content.ActivityNotFoundException anfe) {
									      		activity. startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.mxtech.videoplayer.ad")));
									      	}
					                }
								

								//activity.startActivity(Intent.createChooser(tostart,"Select Media"));
					
								

					}else if(getdefaultMovie()==2){
						Intent tostart = new Intent(activity,ImagesMainActivity.class);
						tostart.putExtra("data",todoItemsmap.get(position).get("data"));
						tostart.putExtra("size",todoItemsmap.get(position).get("size"));
						activity.startActivity(tostart);
						
					}
					
					//vi.setBackgroundResource(R.drawable.app_background_d);
					//Intent intent = new Intent(context, UsingMyWebview.class);
					//intent.putExtra("url",todoItemsmap.get(aposition).get(""+position));
					//startActivity(intent);
				}
				
				
				}
		});
        
        if(position==settings1.getInt(txt_lang.getText()+backgroundImageName, -1)){
        	vi.setBackgroundResource(R.color.bluef5);
            int top = (vi == null)||(settings1.getInt(txt_lang.getText()+backgroundImageName, 0)==0) ? 0 : vi.getTop();
       	 	
            
           // ((ListView) parent).setSelection(settings1.getInt(txt_lang.getText()+backgroundImageName, 0));
           //((ListView) parent).scrollTo(0,settings1.getInt(txt_lang.getText()+backgroundImageName, 0));
            // ((ListView) parent).setSelectionAfterHeaderView();
            ((ListView) parent).setSelectionFromTop(settings1.getInt(txt_lang.getText()+backgroundImageName, 0), top);
           // ((ListView) parent).sets
            
   
            Log.e("Position",settings1.getInt(txt_lang.getText()+backgroundImageName, 0)+">"+top);
        }
        //else if (position == selectedItem)
//        {
//        	vi.setBackgroundResource(R.color.bluef5);
//            int top = (vi == null) ? 0 : vi.getTop();
//            ((ListView) parent).setSelectionFromTop(position, top);
//        } 
         else
        {
        	vi.setBackgroundResource(R.color.whitef5);
          //  vi.setBackgroundResource(R.drawable.app_background_b);
        }
       // vi.setPadding(15, 15, 15, 15);
       // Log.e("IMG URL","http://assholy.3owl.com/thumnail/"+todoItemsmap.get(position).get("thumbnail")+".png");
        imageLoader.DisplayImage("http://assholy.3owl.com/thumnail/"+todoItemsmap.get(position).get("thumbnail")+".png", image);

       
        // ((ListView) parent).sets
         
         notifyDataSetChanged();  
   
        
        }
        return vi;
    }

	public void setData(ArrayList<HashMap<String, String>> listdata) {
		todoItemsmap.clear();
		todoItemsmap=listdata;
		
	}
	public int getdefaultMovie(){
	    SharedPreferences settings1 = activity.getSharedPreferences("One_Piece_Video_By_TomMy", 0);
	    return settings1.getInt("movie_option", 0);
	}
	public ArrayList<HashMap<String, String>> getTodoItemsmap() {
		
		return todoItemsmap;
	}
		
    private int selectedItem;

    public void setSelectedItem(int position) {
        selectedItem = position;
    }
//    public void  isAvailable() {
//		Intent tostart = new Intent(Intent.ACTION_VIEW);
//		tostart.setDataAndType(Uri.parse("http://assholy.3owl.com/onepiece/GE/565.mp4"), "video/*");
// 	   final PackageManager mgr = activity.getPackageManager();
// 	   List<ResolveInfo> list =mgr.queryIntentActivities(tostart, 
// 	         PackageManager.MATCH_DEFAULT_ONLY);
// 	   Log.e("Size",""+list.size());
// 	  if(list.size()<=0){
//     
//      	try {
//      		activity. startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.mxtech.videoplayer.ad")));
//      	} catch (android.content.ActivityNotFoundException anfe) {
//      		activity. startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.mxtech.videoplayer.ad")));
//      	}
//      	activity.finish();
//      }
//     
// 	  
// 	 
// 	
// 	}

    public boolean checkMXPlayer(){
    	try{
    	    activity.getPackageManager().getApplicationInfo("com.mxtech.videoplayer.ad", 0 );
    	    return true;
    	} catch( PackageManager.NameNotFoundException e ){
    	    return false;
    	}
    }
}