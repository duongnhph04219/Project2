package group3.pro205;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class PlaceInfo extends AppCompatActivity {
    private ImageView imageView;
   private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_placeinfo);
        TextView tvadress = (TextView) findViewById(R.id.tvadress);
        TextView tvname = (TextView) findViewById(R.id.tvten);
        TextView tvlongdc = (TextView) findViewById(R.id.tvlongdc);
        imageView=(ImageView)findViewById(R.id.imgvBig) ;
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        Drawable drawable= getResources().getDrawable(R.drawable.back);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 20, 30, true));
        newdrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);


        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(newdrawable);
        Intent it=getIntent();
        tvadress.setText(it.getStringExtra("adress"));
        tvname.setText(it.getStringExtra("name"));
        tvlongdc.setText(it.getStringExtra("longdc"));
         url=it.getStringExtra("urlimg");
        int imgcount= Integer.parseInt(it.getStringExtra("imgcount"));
        LinearLayout layout = (LinearLayout)findViewById(R.id.imgsmall);
        for(int i=0;i<imgcount;i++){
            final int k=i;

            final ImageView image = new ImageView(this);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(100,100));
            image.setMaxHeight(80);
            image.setMaxWidth(80);
            Picasso.with(getApplicationContext())
                    .load("http://bwhere.vn/uploads/small/" + url.split("/")[i])
                    .error(R.mipmap.ic_launcher)
                    .resize(80,80)
                    .placeholder( R.drawable.progress_animation )
                    .into(image);
            layout.addView(image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Picasso.with(getApplicationContext())
                            .load("http://bwhere.vn/uploads/big/" + url.split("/")[k])
                            .error(R.mipmap.ic_launcher)
                            .placeholder( R.drawable.progress_animation )
                            .into(imageView);
                }
            });
        }

        if(imgcount!=0) {
            Picasso.with(getApplicationContext())
                    .load("http://bwhere.vn/uploads/big/" + url.split("/")[0])
                    .error(R.mipmap.ic_launcher)
                    .fit().centerInside()
                    .placeholder( R.drawable.progress_animation )
                    .into(imageView);
        }else{
            imageView.setImageResource(R.drawable.no_image);

        }
        getSupportActionBar().setTitle(it.getStringExtra("name"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
