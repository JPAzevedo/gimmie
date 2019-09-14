package com.gimme.gimmeproject.fragments;

import java.util.List;

public interface GiftDownloaderInterface {
    void onDownloadSuccess(List results);
    void onDownloadError();
}
