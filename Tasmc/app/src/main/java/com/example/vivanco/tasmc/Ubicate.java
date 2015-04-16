package com.example.vivanco.tasmc;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.indooratlas.android.CalibrationState;
import com.indooratlas.android.IndoorAtlas;
import com.indooratlas.android.IndoorAtlasException;
import com.indooratlas.android.IndoorAtlasFactory;
import com.indooratlas.android.IndoorAtlasListener;
import com.indooratlas.android.ServiceState;

import java.util.ArrayList;


public class Ubicate extends ActionBarActivity implements IndoorAtlasListener {

    private ImageView imHir;

    private static final String TAG = "MainActivity";

    private IndoorAtlas mIndoorAtlas;
    private boolean mIsPositioning;
    private StringBuilder mSharedBuilder = new StringBuilder();

    private String mApiKey = "7345a5a1-4f6f-4171-8929-a69480f39e24";
    private String mApiSecret = "0InsCBvum&n5Fotggjfhq&&xJcus%jPqmD9P0WynlApqhpzuv5!mOX2qQMzAK3mkS(X3EtPP%bONtGzX1QTnTRUZpnK&(4G3wf8nzrP&tq!kt4x0!yWZ25hUfMbmyuwZ";

    private String mVenueId = "4734dee1-ea26-417b-8839-aea0f69ae9c7";
    private String mFloorId = "f54a022d-fd7a-4b94-a08c-bd675e7adcb3";
    private String mFloorPlanId = "033fa463-4449-4a08-8a5f-f56b469976e5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicate);
        ImageView main = (ImageView) findViewById(R.id.main);
        main.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //main.setScaleType(ImageView.ScaleType.MATRIX);
        main.setImageResource(R.drawable.plantaalta);

        ImageView imHir = (ImageView) findViewById(R.id.aimHir);
        imHir.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //imHir.setScaleType(ImageView.ScaleType.MATRIX);
        imHir.setImageResource(R.drawable.agregar);
        imHir.getLayoutParams().height = 30;
        imHir.getLayoutParams().width = 30;
        imHir.setX(300);
        imHir.setY(800);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_ubk);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initIndoorAtlas();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        //Si el id seleccionado es igual al del home regresa a la principal
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tearDown();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_log:
                mLogAdapter.clear();
                return true;
            case R.id.action_toggle_positioning:
                togglePositioning();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void tearDown() {
        if (mIndoorAtlas != null) {
            mIndoorAtlas.tearDown();
        }
    }


    private void stopPositioning() {
        mIsPositioning = false;
        if (mIndoorAtlas != null) {
            log("Stop positioning");
            mIndoorAtlas.stopPositioning();
        }
    }

    private void startPositioning() {
        if (mIndoorAtlas != null && mIndoorAtlas.isCalibrationReady()) {
            log(String.format("startPositioning, venueId: %s, floorId: %s, floorPlanId: %s",
                    mVenueId,
                    mFloorId,
                    mFloorPlanId));
            try {
                mIndoorAtlas.startPositioning(mVenueId, mFloorId, mFloorPlanId);
                mIsPositioning = true;
            } catch (IndoorAtlasException e) {
                log("startPositioning failed: " + e);
            }
        } else {
            log("calibration not ready, cannot start positioning");
        }
    }

    private void togglePositioning() {
        if (mIsPositioning) {
            stopPositioning();
        } else {
            startPositioning();
        }
    }

    private void initIndoorAtlas() {

        try {

            log("Connecting with IndoorAtlas, apiKey: " + mApiKey);

            // obtain instance to positioning service, note that calibrating might begin instantly
            mIndoorAtlas = IndoorAtlasFactory.createIndoorAtlas(
                    getApplicationContext(),
                    this, // IndoorAtlasListener
                    mApiKey,
                    mApiSecret);

            mIndoorAtlas.setPreferMobileConnection(true);

            log("IndoorAtlas instance created");

        } catch (IndoorAtlasException ex) {
            Log.e("IndoorAtlas", "init failed", ex);
            log("init IndoorAtlas failed, " + ex.toString());
        }

    }


    private void log(final String msg) {
        Log.d(TAG, msg);
    }

    /* IndoorAtlasListener interface */

    /**
     * This is where you will handle location updates.
     */
    public void onServiceUpdate(ServiceState state) {
        mSharedBuilder.setLength(0);
        mSharedBuilder.append("Location: ")
                .append("\n\troundtrip : ").append(state.getRoundtrip()).append("ms")
                .append("\n\tlat : ").append(state.getGeoPoint().getLatitude())
                .append("\n\tlon : ").append(state.getGeoPoint().getLongitude())
                .append("\n\tX [meter] : ").append(state.getMetricPoint().getX())
                .append("\n\tY [meter] : ").append(state.getMetricPoint().getY())
                .append("\n\tI [pixel] : ").append(state.getImagePoint().getI())
                .append("\n\tJ [pixel] : ").append(state.getImagePoint().getJ())
                .append("\n\theading : ").append(state.getHeadingDegrees())
                .append("\n\tuncertainty: ").append(state.getUncertainty())
                .append("\n\tqueue: ").append(state.getMessagesInQueue());

        log(mSharedBuilder.toString());
        mover(state.getImagePoint().getI(), state.getImagePoint().getJ());
    }

    public void mover(int x, int y){
        Matrix m = new Matrix();
        m.postTranslate(x,y);
        imHir.setImageMatrix(m);
    }

    @Override
    public void onServiceFailure(int errorCode, String reason) {
        log("onServiceFailure: reason : " + reason);
    }

    @Override
    public void onServiceInitializing() {
        log("onServiceInitializing()");
    }

    @Override
    public void onServiceInitialized() {
        log("onServiceInitialized()");
    }

    @Override
    public void onInitializationFailed(final String reason) {
        log("onInitializationFailed(): " + reason);
    }

    @Override
    public void onServiceStopped() {
        log("onServiceStopped()");
    }

    @Override
    public void onCalibrationStatus(CalibrationState calibrationState) {

        log("onCalibrationStatus: event: " + calibrationState.getCalibrationEvent()
                + ", percentage: " + calibrationState.getPercentage());

    }

    @Override
    public void onCalibrationFailed(String reason) {
        log("onCalibrationFailed(): Please do figure '8' motion until " +
                "onCalibrationFinished() or onCalibrationFailed() is called "+ reason);
    }

    @Override
    public void onCalibrationInvalid() {
        log("Calibration Invalid");
    }

    /**
     * Calibration successful, positioning can be started
     */
    @Override
    public void onCalibrationReady() {
        log("onCalibrationReady");
        startPositioning();
    }

    @Override
    public void onNetworkChangeComplete(boolean success) {
        Log.d(TAG, "onNetworkChangeComplete(), success = "+success);

        //showMessageOnUI("onNetworkChangeComplete() success = "+success);
    }


    static class LogAdapter extends BaseAdapter {

        private ArrayList<String> mLines = new ArrayList<String>();
        private LayoutInflater mInflater;

        public LogAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mLines.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView text = (TextView) convertView;
            if (convertView == null) {
                text = (TextView) mInflater.inflate(android.R.layout.simple_list_item_1, parent,
                        false);
            }
            text.setText(mLines.get(position));
            return text;
        }

        public void add(String line) {
            mLines.add(0, line);
        }

        public void clear() {
            mLines.clear();
            notifyDataSetChanged();
        }
    }

}
