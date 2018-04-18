package com.psyclotron.messenger.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.psyclotron.messenger.R;
import com.psyclotron.messenger.activity.Homepage;
import com.psyclotron.messenger.adapter.ChatAdapter;
import com.psyclotron.messenger.adapter.Chats;
import com.psyclotron.messenger.adapter.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private List<Chats> chats;
    private ChatAdapter chatAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static final String TITLE = "Chat";

    public static ChatFragment newInstance() {

        return new ChatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chats=new ArrayList<>();
        View chatview=inflater.inflate(R.layout.fragment_chat, container, false);

        for(int i=0;i<20;i++){
            Chats chat= new Chats();
            chat.setUsername("Prasidha Karki "+i);
            chat.setUsermessage("This is message from prasidha karki with user id"+i);
            chats.add(chat);
        }
        //find view by id and attaching adapter for the RecyclerView
        RecyclerView recyclerView = chatview.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //this was used
        chatAdapter = new ChatAdapter(recyclerView, chats, (Activity) getContext());
        recyclerView.setAdapter(chatAdapter);

        //set load more listener for the RecyclerView adapter
        chatAdapter.setLoadMoreListener(new LoadMoreListner() {

            @Override
            public void LoadMore() {
                if (chats.size() <= 20) {
                    chats.add(null);
                    chatAdapter.notifyItemInserted(chats.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            chats.remove(chats.size() - 1);
                            chatAdapter.notifyItemRemoved(chats.size());

                            //Generating more data
                            int index = chats.size();
                            int end = index + 10;
                            for (int i = index; i < end; i++) {
                                Chats chat= new Chats();
                                chat.setUsername("Prasidha Only"+i);
                                chat.setUsermessage("This is new message from prasidha" + i );
                                chats.add(chat);
                            }
                            chatAdapter.notifyDataSetChanged();
                            chatAdapter.setLoaded();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(getContext(), "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return chatview;  //inflater.inflate(R.layout.fragment_chat, container, false);
    }

}
