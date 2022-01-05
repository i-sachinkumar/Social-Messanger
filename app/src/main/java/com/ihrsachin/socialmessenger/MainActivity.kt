package com.ihrsachin.socialmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    val ANONYMOUS = "anonymous"
    val DEFAULT_MSG_LENGTH_LIMIT = 1000

    private var mMessageListView: ListView? = null
    private var mMessageAdapter: MessageAdapter? = null
    private var mProgressBar: ProgressBar? = null
    private var mPhotoPickerButton: ImageButton? = null
    private var mMessageEditText: EditText? = null
    private var mSendButton: Button? = null

    private var mUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         mUsername = ANONYMOUS;

        // Initialize references to views
        mProgressBar = findViewById(R.id.progressBar)
        mMessageListView = findViewById(R.id.messageListView)
        mPhotoPickerButton = findViewById(R.id.photoPickerButton)
        mMessageEditText = findViewById(R.id.messageEditText)
        mSendButton = findViewById(R.id.sendButton)

//        // Initialize message ListView and its adapter
//        List<FriendlyMessage> friendlyMessages = new ArrayList<>();
//        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
//        mMessageListView.setAdapter(mMessageAdapter);

        var socialMessages : List<SocialMessage>? = null
        mMessageAdapter = MessageAdapter(this, R.layout.item_message, socialMessages)
        mMessageListView.adapter = mMessageAdapter

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Fire an intent to show an image picker
            }
        });

        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click

                // Clear input box
                mMessageEditText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}