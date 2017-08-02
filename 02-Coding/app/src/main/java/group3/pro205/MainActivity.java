package group3.pro205;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import group3.pro205.common.AppContanst;
import group3.pro205.entry.EnCategory;
import group3.pro205.entry.Enplace;
import group3.pro205.services.AppApi;

public class MainActivity extends AppCompatActivity implements LocationListener, View.OnClickListener {
    private GoogleMap myMap;
    private Map<Marker, String> markeradress = new HashMap<>();
    private Map<Marker, String> markerlongdc = new HashMap<>();
    private Map<Marker, String> markerImage = new HashMap<>();
    private Map<Marker, String> imgcount = new HashMap<>();
    private ProgressDialog myProgress;
    private static final String MYTAG = "MYTAG";
    public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;
    private AppApi appApi;
    private ImageButton imgout;
    private ImageButton imgsence;
    private ImageButton imgpagoda;
    private ImageButton imgrestaurant;
    private ImageButton imgatm;
    private ImageButton imgful;
    private int countPress, countPress2, countPress3, countPress4, countPress5;
    private ArrayList<Enplace.Data> listPlace;
    private ArrayList<Enplace.Data> markersence;
    private ArrayList<Enplace.Data> markerrestaurent;
    private ArrayList<Enplace.Data> markerpagoda;
    private ArrayList<Enplace.Data> markerful;
    private Boolean active1;
    private Boolean active2;
    private Boolean active3;
    private Boolean active5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        listPlace = new ArrayList<Enplace.Data>();
        markersence = new ArrayList<Enplace.Data>();
        markerpagoda = new ArrayList<Enplace.Data>();
        markerful = new ArrayList<Enplace.Data>();
        markerrestaurent = new ArrayList<Enplace.Data>();
        active1 = false;
        active2 = false;
        active3 = false;
        active5 = false;
        appApi = new AppApi();
        imgout = (ImageButton) findViewById(R.id.imgout);
        ImageButton imgin = (ImageButton) findViewById(R.id.imgin);
        imgsence = (ImageButton) findViewById(R.id.imgscene);
        imgpagoda = (ImageButton) findViewById(R.id.imgpagoda);
        imgrestaurant = (ImageButton) findViewById(R.id.imgrestaurant);
        imgatm = (ImageButton) findViewById(R.id.imgatm);
        imgful = (ImageButton) findViewById(R.id.imgful);
        imgout.setBackgroundColor(0);
        imgin.setBackgroundColor(0);
        imgsence.setBackgroundColor(0);
        imgpagoda.setBackgroundColor(0);
        imgrestaurant.setBackgroundColor(0);
        imgatm.setBackgroundColor(0);
        imgful.setBackgroundColor(0);
        imgsence.setOnClickListener(this);
        imgpagoda.setOnClickListener(this);
        imgrestaurant.setOnClickListener(this);
        imgatm.setOnClickListener(this);
        imgful.setOnClickListener(this);
        RelativeLayout menu = (RelativeLayout) findViewById(R.id.menucatalory);
        menu.setVisibility(View.GONE);
        RelativeLayout inbt = (RelativeLayout) findViewById(R.id.inbt);
        inbt.setVisibility(View.GONE);
        imgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout menu = (RelativeLayout) findViewById(R.id.menucatalory);
                menu.setVisibility(View.VISIBLE);
                RelativeLayout inbt = (RelativeLayout) findViewById(R.id.inbt);
                inbt.setVisibility(View.VISIBLE);
                imgout.setVisibility(View.GONE);
            }
        });
        imgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout menu = (RelativeLayout) findViewById(R.id.menucatalory);
                menu.setVisibility(View.GONE);
                RelativeLayout inbt = (RelativeLayout) findViewById(R.id.inbt);
                inbt.setVisibility(View.GONE);
                imgout.setVisibility(View.VISIBLE);
            }
        });
        myProgress = new ProgressDialog(this);
        getAllCategory();
        getAllPlace();
        myProgress.setTitle("Map Loading ...");
        myProgress.setMessage("Please wait...");
        myProgress.setCancelable(true);
        myProgress.show();

        SupportMapFragment mapFragment
                = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                onMyMapReady(googleMap);
                myMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imgscene:

                countPress += 1;
                Log.d("count", countPress + "");
                if (countPress % 2 != 0) {
                    myMap.clear();
                    imgsence.setAlpha(0.3f);
                    setMaker(markersence);
                    active1 = true;

                } else {
                    myMap.clear();
                    imgsence.setAlpha(1f);
                    allmarker(listPlace);
                    active1 = false;
                }
                break;
            case R.id.imgrestaurant:

                countPress2 += 1;
                if (countPress2 % 2 != 0) {
                    myMap.clear();
                    imgrestaurant.setAlpha(0.3f);
                    setMaker2(markerrestaurent);
                    active2 = true;
                } else {
                    myMap.clear();
                    imgrestaurant.setAlpha(1f);
                    allmarker(listPlace);
                    active2 = false;
                }
                break;
            case R.id.imgpagoda:

                countPress3 += 1;
                if (countPress3 % 2 != 0) {
                    myMap.clear();
                    imgpagoda.setAlpha(0.3f);
                    setMaker3(markerpagoda);
                    active3 = true;
                } else {
                    myMap.clear();
                    imgpagoda.setAlpha(1f);
                    allmarker(listPlace);
                    active3 = false;
                }
                break;
            case R.id.imgatm:

                countPress4 += 1;
                if (countPress4 % 2 != 0) {

                    imgatm.setAlpha(0.3f);


                } else {

                    imgatm.setAlpha(1f);

                }
                break;
            case R.id.imgful:

                countPress5 += 1;
                if (countPress5 % 2 != 0) {
                    myMap.clear();
                    imgful.setAlpha(0.3f);
                    setMaker5(markerful);
                    active5 = true;
                } else {
                    myMap.clear();
                    imgful.setAlpha(1f);
                    allmarker(listPlace);
                    active5 = false;
                }
                break;
        }

    }

    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View myContentsView;

        MyInfoWindowAdapter() {
            myContentsView = getLayoutInflater().inflate(R.layout.activity_info, null);
        }

        @Override
        public View getInfoContents(Marker marker) {
            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.tvname));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.tvdc));
            tvSnippet.setText(marker.getSnippet().split("/")[0]);
            myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(650, 250));
            ImageView image = (ImageView) myContentsView.findViewById(R.id.imview);
            String urlImage = marker.getSnippet().split("/")[1];
            Picasso.with(getApplicationContext())
                    .load("http://bwhere.vn/uploads/small/" + urlImage)
                    .error(R.mipmap.ic_launcher)
                    .resize(200, 200)
                    .into(image);
            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {

            return null;
        }

    }

    private void getAllCategory() {
        myProgress.setTitle("Loading");
        myProgress.setCancelable(false);
        myProgress.show();
        appApi.service().getAllCategory(new Callback<EnCategory>() {
            @Override
            public void success(EnCategory enCategory, Response response) {
                myProgress.cancel();
                ArrayList<EnCategory.Data> listCategory = new ArrayList<EnCategory.Data>();
                listCategory = enCategory.getData();
                for (int i = 0; i < listCategory.size(); i++) {
                    listCategory.get(i).getId();
                }
            }

            @Override
            public void failure(RetrofitError error) {

                myProgress.cancel();
            }
        });
    }

    private void getAllPlace() {
        myProgress.setTitle("Loading");
        myProgress.setCancelable(false);
        myProgress.show();
        appApi.service().getAllplace(new Callback<Enplace>() {
            @Override
            public void success(Enplace enplace, Response response) {
                AppContanst.LIST_ENPLACE = enplace.getData();
                myProgress.cancel();
                markerrestaurent = enplace.getData();
                markersence = enplace.getData();
                markerful = enplace.getData();
                markerpagoda = enplace.getData();
                listPlace = enplace.getData();
                setMaker(markersence);
                setMaker2(markerrestaurent);
                setMaker3(markerpagoda);
                setMaker5(markerful);
                allmarker(listPlace);
                myMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Intent it = new Intent(MainActivity.this, PlaceInfo.class);
                        it.putExtra("name", marker.getTitle());
                        it.putExtra("adress", markeradress.get(marker));
                        it.putExtra("longdc", markerlongdc.get(marker));
                        it.putExtra("urlimg", markerImage.get(marker));
                        it.putExtra("imgcount", imgcount.get(marker));
                        startActivity(it);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

    }

    private void allmarker(ArrayList<Enplace.Data> listData) {
        for (int i = 0; i < listData.size(); i++) {
            {
                double lat = Double.parseDouble(listData.get(i).getLatitude());
                double lg = Double.parseDouble(listData.get(i).getLongitude());
                LatLng TTTH_KHTN = new LatLng(lat, lg);
                MarkerOptions option = new MarkerOptions();
                option.position(TTTH_KHTN);

                if (listData.get(i).getCover().size() != 0) {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + listData.get(i).getCover().get(0).getUrl());
                } else {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + " ");
                }
                switch (listData.get(i).category_id) {
                    case 0:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category10));
                        break;
                    case 1:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category1));
                        break;
                    case 2:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category6));
                        break;
                    case 3:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category9));
                        break;
                    case 4:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category4));
                        break;
                    case 5:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category2));
                        break;
                    case 6:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category5));
                        break;
                    case 7:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category12));
                        break;
                    case 8:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category8));
                        break;
                    case 9:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category11));
                        break;
                    case 10:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category14));
                        break;
                    case 11:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category17));
                        break;
                    case 12:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category7));
                        break;
                    case 13:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category15));
                        break;
                }
                final Marker maker = myMap.addMarker(option);
                markeradress.put(maker, listData.get(i).getAddress_vi());
                markerlongdc.put(maker, listData.get(i).getDescription_vi());
                imgcount.put(maker, listData.get(i).images_count + "");
                String url = "";
                for (int k = 0; k < listData.get(i).getImages_count(); k++) {
                    url += listData.get(i).getImages().get(k).getUrl() + "/";
                    markerImage.put(maker, url);
                }
            }

        }

    }

    private void setMaker(ArrayList<Enplace.Data> listData) {
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).category_id != 2 && listData.get(i).category_id != 3 && listData.get(i).category_id != 4 && listData.get(i).category_id != 5 && listData.get(i).category_id != 6 && listData.get(i).category_id != 7 && listData.get(i).category_id != 10 && listData.get(i).category_id != 12 && listData.get(i).category_id != 13) {

                double lat = Double.parseDouble(listData.get(i).getLatitude());
                double lg = Double.parseDouble(listData.get(i).getLongitude());
                LatLng TTTH_KHTN = new LatLng(lat, lg);
                MarkerOptions option = new MarkerOptions();
                option.position(TTTH_KHTN);

                if (listData.get(i).getCover().size() != 0) {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + listData.get(i).getCover().get(0).getUrl());
                } else {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + " ");
                }
                switch (listData.get(i).category_id) {
                    case 0:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category10));
                        break;
                    case 1:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category1));
                        break;

                    case 8:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category8));
                        break;
                    case 9:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category11));
                        break;
                    case 11:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category17));
                        break;
                }

                final Marker maker = myMap.addMarker(option);
                if (active2) {
                    if (listData.get(i).category_id == 8) {
                        maker.remove();
                    }
                }
                if (active3) {
                    if (listData.get(i).category_id == 1) {
                        maker.remove();
                    }
                }
                if (active5) {
                    if (listData.get(i).category_id == 0) {
                        maker.remove();
                    }
                }

                markeradress.put(maker, listData.get(i).getAddress_vi());
                markerlongdc.put(maker, listData.get(i).getDescription_vi());
                imgcount.put(maker, listData.get(i).images_count + "");
                String url = "";
                for (int k = 0; k < listData.get(i).getImages_count(); k++) {
                    url += listData.get(i).getImages().get(k).getUrl() + "/";
                    markerImage.put(maker, url);
                }

            }


        }

    }

    private void setMaker2(ArrayList<Enplace.Data> listData) {
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).category_id != 8) {

                double lat = Double.parseDouble(listData.get(i).getLatitude());
                double lg = Double.parseDouble(listData.get(i).getLongitude());
                LatLng TTTH_KHTN = new LatLng(lat, lg);
                MarkerOptions option = new MarkerOptions();
                option.position(TTTH_KHTN);

                if (listData.get(i).getCover().size() != 0) {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + listData.get(i).getCover().get(0).getUrl());
                } else {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + " ");
                }

                switch (listData.get(i).category_id) {
                    case 0:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category10));
                        break;
                    case 1:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category1));
                        break;
                    case 2:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category6));
                        break;
                    case 3:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category9));
                        break;
                    case 4:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category4));
                        break;
                    case 5:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category2));
                        break;
                    case 6:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category5));
                        break;
                    case 7:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category12));
                        break;
                    case 9:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category11));
                        break;
                    case 10:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category14));
                        break;
                    case 11:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category17));
                        break;
                    case 12:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category7));
                        break;
                    case 13:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category15));
                        break;
                }

                final Marker maker = myMap.addMarker(option);
                if (active1) {
                    if (listData.get(i).category_id == 2 || listData.get(i).category_id == 3 || listData.get(i).category_id == 4 || listData.get(i).category_id == 5 || listData.get(i).category_id == 6 || listData.get(i).category_id == 7 || listData.get(i).category_id == 10 || listData.get(i).category_id == 12 || listData.get(i).category_id == 13) {
                        maker.remove();
                    }
                }
                if (active3) {
                    if (listData.get(i).category_id == 1) {
                        maker.remove();
                    }
                }
                if (active5) {
                    if (listData.get(i).category_id == 0) {
                        maker.remove();
                    }
                }
                markeradress.put(maker, listData.get(i).getAddress_vi());
                markerlongdc.put(maker, listData.get(i).getDescription_vi());
                imgcount.put(maker, listData.get(i).images_count + "");
                String url = "";
                for (int k = 0; k < listData.get(i).getImages_count(); k++) {
                    url += listData.get(i).getImages().get(k).getUrl() + "/";
                    markerImage.put(maker, url);
                }

            }

        }
    }

    private void setMaker3(ArrayList<Enplace.Data> listData) {
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).category_id != 1) {

                double lat = Double.parseDouble(listData.get(i).getLatitude());
                double lg = Double.parseDouble(listData.get(i).getLongitude());
                LatLng TTTH_KHTN = new LatLng(lat, lg);
                MarkerOptions option = new MarkerOptions();
                option.position(TTTH_KHTN);

                if (listData.get(i).getCover().size() != 0) {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + listData.get(i).getCover().get(0).getUrl());
                } else {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + " ");
                }

                switch (listData.get(i).category_id) {
                    case 0:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category10));
                        break;
                    case 2:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category6));
                        break;
                    case 3:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category9));
                        break;
                    case 4:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category4));
                        break;
                    case 5:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category2));
                        break;
                    case 6:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category5));
                        break;
                    case 7:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category12));
                        break;
                    case 8:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category8));
                        break;
                    case 9:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category11));
                        break;
                    case 10:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category14));
                        break;
                    case 11:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category17));
                        break;
                    case 12:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category7));
                        break;
                    case 13:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category15));
                        break;
                }

                final Marker maker = myMap.addMarker(option);
                if (active1) {
                    if (listData.get(i).category_id == 2 || listData.get(i).category_id == 3 || listData.get(i).category_id == 4 || listData.get(i).category_id == 5 || listData.get(i).category_id == 6 || listData.get(i).category_id == 7 || listData.get(i).category_id == 10 || listData.get(i).category_id == 12 || listData.get(i).category_id == 13) {
                        maker.remove();
                    }
                }
                if (active2) {
                    if (listData.get(i).category_id == 8) {
                        maker.remove();
                    }
                }
                if (active5) {
                    if (listData.get(i).category_id == 0) {
                        maker.remove();
                    }
                }
                markeradress.put(maker, listData.get(i).getAddress_vi());
                markerlongdc.put(maker, listData.get(i).getDescription_vi());
                imgcount.put(maker, listData.get(i).images_count + "");
                String url = "";
                for (int k = 0; k < listData.get(i).getImages_count(); k++) {
                    url += listData.get(i).getImages().get(k).getUrl() + "/";
                    markerImage.put(maker, url);
                }

            }

        }
    }

    private void setMaker5(ArrayList<Enplace.Data> listData) {
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).category_id != 0) {

                double lat = Double.parseDouble(listData.get(i).getLatitude());
                double lg = Double.parseDouble(listData.get(i).getLongitude());
                LatLng TTTH_KHTN = new LatLng(lat, lg);
                MarkerOptions option = new MarkerOptions();
                option.position(TTTH_KHTN);

                if (listData.get(i).getCover().size() != 0) {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + listData.get(i).getCover().get(0).getUrl());
                } else {
                    option.title(listData.get(i).getName_vi()).snippet(listData.get(i)
                            .getShort_description_vi() + "/" + " ");
                }

                switch (listData.get(i).category_id) {
                    case 1:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category1));
                        break;
                    case 2:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category6));
                        break;
                    case 3:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category9));
                        break;
                    case 4:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category4));
                        break;
                    case 5:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category2));
                        break;
                    case 6:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category5));
                        break;
                    case 7:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category12));
                        break;
                    case 8:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category8));
                        break;
                    case 9:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category11));
                        break;
                    case 10:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category14));
                        break;
                    case 11:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category17));
                        break;
                    case 12:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category7));
                        break;
                    case 13:
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.category15));
                        break;
                }

                final Marker maker = myMap.addMarker(option);
                if (active1) {
                    if (listData.get(i).category_id == 2 || listData.get(i).category_id == 3 || listData.get(i).category_id == 4 || listData.get(i).category_id == 5 || listData.get(i).category_id == 6 || listData.get(i).category_id == 7 || listData.get(i).category_id == 10 || listData.get(i).category_id == 12 || listData.get(i).category_id == 13) {
                        maker.remove();
                    }
                }
                if (active2) {
                    if (listData.get(i).category_id == 8) {
                        maker.remove();
                    }
                }
                if (active3) {
                    if (listData.get(i).category_id == 1) {
                        maker.remove();
                    }
                }
                markeradress.put(maker, listData.get(i).getAddress_vi());
                markerlongdc.put(maker, listData.get(i).getDescription_vi());
                imgcount.put(maker, listData.get(i).images_count + "");
                String url = "";
                for (int k = 0; k < listData.get(i).getImages_count(); k++) {
                    url += listData.get(i).getImages().get(k).getUrl() + "/";
                    markerImage.put(maker, url);
                }

            }

        }
    }

    private void onMyMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        myMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                myProgress.dismiss();
                askPermissionsAndShowMyLocation();
            }
        });
        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        myMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        myMap.setMyLocationEnabled(true);
    }

    private void askPermissionsAndShowMyLocation() {


        if (Build.VERSION.SDK_INT >= 23) {
            int accessCoarsePermission
                    = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            int accessFinePermission
                    = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);


            if (accessCoarsePermission != PackageManager.PERMISSION_GRANTED
                    || accessFinePermission != PackageManager.PERMISSION_GRANTED) {


                String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION};


                ActivityCompat.requestPermissions(this, permissions,
                        REQUEST_ID_ACCESS_COURSE_FINE_LOCATION);

                return;
            }
        }


        this.showMyLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case REQUEST_ID_ACCESS_COURSE_FINE_LOCATION: {


                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();


                    this.showMyLocation();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }


    private String getEnabledLocationProvider() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        Criteria criteria = new Criteria();


        String bestProvider = locationManager.getBestProvider(criteria, true);

        boolean enabled = locationManager.isProviderEnabled(bestProvider);

        if (!enabled) {
            Toast.makeText(this, "No location provider enabled!", Toast.LENGTH_LONG).show();
            Log.i(MYTAG, "No location provider enabled!");
            return null;
        }
        return bestProvider;
    }


    private void showMyLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        String locationProvider = this.getEnabledLocationProvider();

        if (locationProvider == null) {
            return;
        }


        final long MIN_TIME_BW_UPDATES = 1000;

        final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;

        Location myLocation = null;
        try {


            locationManager.requestLocationUpdates(
                    locationProvider,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);


            myLocation = locationManager
                    .getLastKnownLocation(locationProvider);
        } catch (SecurityException e) {
            Toast.makeText(this, "Show My Location Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }

        if (myLocation != null) {

            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)             // Sets the center of the map to location user
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}


