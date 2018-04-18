package com.psyclotron.messenger.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.psyclotron.messenger.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private LoadMoreListner loadMoreListner;
    private boolean isLoading;
    private Activity activity;
    private List<Chats> chats;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;


    public ChatAdapter(RecyclerView recyclerView, List<Chats> chats, Activity activity) {
        this.chats = chats;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (loadMoreListner != null) {
                        loadMoreListner.LoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }


    public void setLoadMoreListener(LoadMoreListner mLoadMoreListener) {
        this.loadMoreListner = mLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return chats.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.fragment_chat_list, parent, false);
            return new ChatViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof ChatViewHolder) {
            Chats chat= chats.get(position);
            ChatViewHolder chatViewHolder = (ChatViewHolder) holder;
            chatViewHolder.username.setText(chat.getUsername());
            chatViewHolder.usermessage.setText(chat.getUsermessage());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return chats == null ? 0 : chats.size();    }


    private class ChatViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public TextView usermessage;


        public ChatViewHolder (View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.id_user_name);
            usermessage = (TextView) view.findViewById(R.id.id_user_shortmessage);
        }


    }
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.load_more_progressbar);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }
}
