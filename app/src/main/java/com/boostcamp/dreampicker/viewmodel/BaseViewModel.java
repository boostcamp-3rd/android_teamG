package com.boostcamp.dreampicker.viewmodel;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable;

    BaseViewModel() {
        compositeDisposable = new CompositeDisposable();
        initViewModel();
    }

    void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract void initViewModel();
}
