# MultiStatusView

## Introduction
Load multiple status view such as loading,error,net error,content page.

## Install

### gradle
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency in your module build.gradle 

	dependencies {
		 compile 'com.github.fengminchao:MultiStatusView:0.5'
	}
 
## Usage

### 1.Write MultiStatusView in your layout file
```xml
<com.muxistudio.multistatusview.MultiStatusView
    android:layout_width="match_parent"
    android:id="@+id/multi_status_view"
    app:errorView="@layout/custom_error_view"
    app:emptyView="@layout/custom_empty_view"
    app:contentView="@layout/custom_content_view"
    app:netErrorView="@layout/custom_net_error_view"
    app:loadingView="@layout/custom_loading_view"
    android:layout_height="match_parent"/>
```

### 2.Write status view layout file
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/view_empty"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/iv_empty"
        android:layout_width="266dp"
        android:layout_height="200dp"
        android:layout_centerHorizontal="true"
        android:scaleType="centerInside"
        android:src="@drawable/bg_empty"/>
        
     <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/view_retry"
        android:layout_centerHorizontal="true"
        android:layout_below="@+id/iv_empty"
        android:text="出错啦，点击重试"/>
        

</RelativeLayout>
```

The id of status view should be following value
- view_empty
- view_content
- view_loading
- view_error
- view_net_error

The MultiStatusView will listen onRetry event when you add a view with id "view_retry".

### 3.Java file
```java
MultiStatusView multiStatusView = findViewById(R.id.multi_status_view);
//show empty view
multiStatusView.showEmpty();
//show loading view
multiStatusView.showEmpty();
//show content view
multiStatusView.showContent();
//show error view
multiStatusView.showError();
//show net error view
multiStatusView.showNetError();

// get content view 
View view = multiStatusView.getContentView();

// set onRetryListener
multiStatusView.setOnRetryListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
           reload();
     }
});
```

