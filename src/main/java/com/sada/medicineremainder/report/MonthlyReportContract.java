package com.sada.medicineremainder.report;

import com.sada.medicineremainder.BasePresenter;
import com.sada.medicineremainder.BaseView;
import com.sada.medicineremainder.data.source.History;
import java.util.List;

/**
 * Created by gautam on 13/07/17.
 */

public interface MonthlyReportContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showHistoryList(List<History> historyList);

        void showLoadingError();

        void showNoHistory();

        void showTakenFilterLabel();

        void showIgnoredFilterLabel();

        void showAllFilterLabel();

        void showNoTakenHistory();

        void showNoIgnoredHistory();

        boolean isActive();

        void showFilteringPopUpMenu();

    }

    interface Presenter extends BasePresenter {

        void loadHistory(boolean showLoading);

        void setFiltering(FilterType filterType);

        FilterType getFilterType();
    }
}
