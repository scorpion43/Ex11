package dariusz.scorpion43.ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button[] buttons;

    private static int getQuestionID( final Resources res, final int index ) {

        final String[] questions = res.getStringArray(R.array.questions);

        return res.getIdentifier(
                questions[index],
                "array",
                "dariusz.scorpion43.ex1");
    }

    private int getQuestionIndex() {
        return getIntent().getIntExtra("Ex1.Question", 0);
    }

    private void initQuestionScreen() {
        final TextView question = (TextView)findViewById(R.id.question);

        final ViewGroup answers = (ViewGroup)findViewById(R.id.answers);

        final Resources resources = getResources();
        final int questionID = getQuestionID(
                resources,
                getQuestionIndex());

        final String[] quesionData =
                resources.getStringArray(questionID);

        question.setText(quesionData[0]);

        final int answerCount = quesionData.length - 1;

        buttons = new Button[answerCount];

        for(int i = 0; i < answerCount; i++) {
            final String answer = quesionData[i + 1];
            final Button button = new Button(this);

            button.setText(answer);
            button.setOnClickListener((View.OnClickListener) this);

            answers.addView(button);
            buttons[i] = button;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initQuestionScreen();

    }

    public void onClick(final View clicked) {
        final String[] questions = getResources().getStringArray(
                R.array.questions);

        if(getQuestionIndex() + 1 >= questions.length) {
            Toast.makeText(
                    this,
                    R.string.noMoreQuestions,
                    Toast.LENGTH_SHORT).show();
        } else {
            final Intent intent = new Intent(
                    MainActivity.this,
                    MainActivity.class);

            intent.putExtra("Ex1.Question", getQuestionIndex() + 1);

            startActivity(intent);
        }
    }
}
