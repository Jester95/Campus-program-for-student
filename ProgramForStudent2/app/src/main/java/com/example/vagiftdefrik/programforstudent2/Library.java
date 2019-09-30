package com.example.vagiftdefrik.programforstudent2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Library extends Fragment {


  private WebView mWebView;  // Webview 선언
  private static final String URL1 = "http://libnt.smuc.ac.kr/";
  // URL1=상명대 도서관 홈페이지 주소


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.library, container, false);

    mWebView = (WebView) v.findViewById(R.id.WebView1);

    mWebView.getSettings().setJavaScriptEnabled(true);
    // Webview에서 JavaScript사용 가능 하도록 설정

    mWebView.loadUrl(URL1);  // Webview에서 불러올 URL

    mWebView.setWebViewClient(new WebViewClient());
    // WebViewClient를 지정
    return v;
  }

//
//  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
//    if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//      mWebView.goBack();
//      return true;
//    }
//    return super.onKeyDown(keyCode, event);
//  }
//
//  @Override public void onBackPressed() {
//    new android.support.v7.app.AlertDialog.Builder(getActivity()).setTitle("종료").setMessage("종료 하시겠습니까?")
//        .setNegativeButton("아니요", null)
//        .setPositiveButton("예", new DialogInterface.OnClickListener() {
//          public void onClick(DialogInterface dialog, int whichButton) {
//            finish(); // ‘예’ Button을 눌렀을 경우, 종료
//          }
//        }).show();
//  }


  private class WishWebViewClient extends WebViewClient {

    @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
      view.loadUrl(url);
      return true;

    }

  }
}