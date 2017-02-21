package com.muxistudio.multistatusview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by ybao on 17/2/14.
 */

public class MultiStatusView extends RelativeLayout {

    private View mErrorView;
    private View mLoadingView;
    private View mEmptyView;
    private View mContentView;
    private View mNetErrorView;

    private View mOnRetryView;

    private int mErrorViewId;
    private int mLoadingViewId;
    private int mEmptyViewId;
    private int mContentViewId;
    private int mNetErrorViewId;

    private int status = 1;

    public static final int STATUS_LOADING = 0;
    public static final int STATUS_CONTENT = 1;
    public static final int STATUS_ERROR = 2;
    public static final int STATUS_EMPTY = 3;
    public static final int STATUS_NET_ERROR = 4;

    private final LayoutParams mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);

    private LayoutInflater mLayoutInflater;
    private OnClickListener mOnRetryListener;

    public MultiStatusView(Context context) {
        this(context, null);
    }

    public MultiStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiStatusView,
                defStyleAttr, 0);

        mErrorViewId = a.getResourceId(R.styleable.MultiStatusView_errorView, 0);
        mLoadingViewId = a.getResourceId(R.styleable.MultiStatusView_loadingView, 0);
        mEmptyViewId = a.getResourceId(R.styleable.MultiStatusView_emptyView, 0);
        mContentViewId = a.getResourceId(R.styleable.MultiStatusView_contentView, 0);
        mNetErrorViewId = a.getResourceId(R.styleable.MultiStatusView_netErrorView, 0);

        a.recycle();

        mLayoutInflater = LayoutInflater.from(context);

    }

    public void showLoading() {
        if (mLoadingView == null && mLoadingViewId != 0) {
            mLoadingView = mLayoutInflater.inflate(mLoadingViewId, null);
            setListener(mLoadingView);
            this.addView(mLoadingView, mLayoutParams);
        }
        setStatus(STATUS_LOADING);
        showView(status);
    }

    public void showContent() {
        if (mContentView == null && mContentViewId != 0) {
            mContentView = mLayoutInflater.inflate(mContentViewId, null);
            setListener(mContentView);
            this.addView(mContentView, mLayoutParams);
        }
        setStatus(STATUS_CONTENT);
        showView(status);
    }

    public void showError() {
        if (mErrorView == null && mErrorViewId != 0) {
            mErrorView = mLayoutInflater.inflate(mErrorViewId, null);
            setListener(mErrorView);
            this.addView(mErrorView, mLayoutParams);
        }
        setStatus(STATUS_ERROR);
        showView(status);
    }

    public void showEmpty() {
        if (mEmptyView == null && mEmptyViewId != 0) {
            mEmptyView = mLayoutInflater.inflate(mEmptyViewId, null);
            setListener(mEmptyView);
            this.addView(mEmptyView, mLayoutParams);
        }
        setStatus(STATUS_EMPTY);
        showView(status);
    }

    public void showNetError() {
        if (mNetErrorView == null) {
            mNetErrorView = mLayoutInflater.inflate(mNetErrorViewId, null);
            setListener(mNetErrorView);
            this.addView(mNetErrorView, mLayoutParams);
        }
        setStatus(STATUS_NET_ERROR);
        showView(status);
    }

    public View getContentView() {
        if (mContentView != null) {
            return mContentView;
        }
        return null;
    }

    public void setListener(View layout) {
        if (layout == null) {
            return;
        }
        mOnRetryView = layout.findViewById(R.id.view_retry);
        if (mOnRetryView != null && mOnRetryListener != null) {
            mOnRetryView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRetryListener.onClick(view);
                }
            });
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    /**
     * invoke before show status view
     * @param onRetryListener
     */
    public void setOnRetryListener(OnClickListener onRetryListener) {
        mOnRetryListener = onRetryListener;
    }

    public void showView(int status) {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(status == STATUS_LOADING ? VISIBLE : GONE);
        }
        if (mContentView != null) {
            mContentView.setVisibility(status == STATUS_CONTENT ? VISIBLE : GONE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(status == STATUS_EMPTY ? VISIBLE : GONE);
        }
        if (mNetErrorView != null) {
            mNetErrorView.setVisibility(status == STATUS_NET_ERROR ? VISIBLE : GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(status == STATUS_ERROR ? VISIBLE : GONE);
        }
    }

}
