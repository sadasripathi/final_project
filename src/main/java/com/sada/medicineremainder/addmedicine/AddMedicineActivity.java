package com.sada.medicineremainder.addmedicine;

import android.os.PersistableBundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.sada.medicineremainder.Injection;
import com.sada.medicineremainder.R;
import com.sada.medicineremainder.utils.ActivityUtils;

public class AddMedicineActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TASK = 1;

    public static final String SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY";

    public static final String EXTRA_TASK_ID = "task_extra_id";
    public static final String EXTRA_TASK_NAME = "task_extra_name";

    private AddMedicinePresenter mAddMedicinePresenter;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        //Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        AddMedicineFragment addMedicineFragment = (AddMedicineFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        int medId = getIntent().getIntExtra(AddMedicineFragment.ARGUMENT_EDIT_MEDICINE_ID,0);
        String medName = getIntent().getStringExtra(AddMedicineFragment.ARGUMENT_EDIT_MEDICINE_NAME);

        setToolbarTitle(medName);


        if (addMedicineFragment == null) {
            addMedicineFragment = AddMedicineFragment.newInstance();

            if (getIntent().hasExtra(AddMedicineFragment.ARGUMENT_EDIT_MEDICINE_ID)) {
                Bundle bundle = new Bundle();
                bundle.putInt(AddMedicineFragment.ARGUMENT_EDIT_MEDICINE_ID, medId);
                addMedicineFragment.setArguments(bundle);
            }
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addMedicineFragment, R.id.contentFrame);
        }

        boolean shouldLoadDataFromRepo = true;

        if (savedInstanceState != null) {

            shouldLoadDataFromRepo = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY);
        }

 // Creating the presenter
        mAddMedicinePresenter = new AddMedicinePresenter(
                medId,
                Injection.provideMedicineRepository(getApplicationContext()),
                addMedicineFragment,
                shouldLoadDataFromRepo);

    }

    public void setToolbarTitle(String medicineName) {
        if (medicineName == null) {
            mActionBar.setTitle(getString(R.string.new_medicine));
        } else {
            mActionBar.setTitle(medicineName);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, mAddMedicinePresenter.isDataMissing());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
