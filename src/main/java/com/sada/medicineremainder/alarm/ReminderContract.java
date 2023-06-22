package com.sada.medicineremainder.alarm;

import com.sada.medicineremainder.BasePresenter;
import com.sada.medicineremainder.BaseView;
import com.sada.medicineremainder.data.source.History;
import com.sada.medicineremainder.data.source.MedicineAlarm;

public interface ReminderContract {

    interface View extends BaseView<Presenter> {

        void showMedicine(MedicineAlarm medicineAlarm);

        void showNoData();

        boolean isActive();

        void onFinish();

    }

    interface Presenter extends BasePresenter {

        void finishActivity();

        void onStart(long id);

        void loadMedicineById(long id);

        void addPillsToHistory(History history);

    }
}
