package com.example.android.basketcounter.fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.model.Stats;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.utils.Utils;
import com.example.android.basketcounter.viewmodel.MatchViewModel;
import com.example.android.basketcounter.viewmodel.StatsViewModel;
import com.example.android.basketcounter.viewmodel.TeamViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class CounterFragment extends Fragment {

    private TeamViewModel teamModel;

    private Spinner homeSpinner;
    private Spinner visitSpinner;
    private TextView homePointsView;
    private TextView visitPointsView;
    private TextView homeFoulsView;
    private TextView visitFoulsView;
    private TextView possession;
    private TextView quarter;
    private TextView quarterTime;

    private Team teamHome;
    private Team teamVisit;
    private List<Team> teamList;

    private CountDownTimer quarterCountDown;
    private CountDownTimer possessionCountDown;

    private long possessionTemp = Utils.POSSESSION_TIME;
    private boolean possessionRunning;

    private long quarterTemp = Utils.QUARTER_TIME;
    private boolean quarterRunning;
    private int quarterNumber = 1;

    private int homePoints;
    private int visitPoints;
    private int[] homeFouls = new int[5];
    private int[] visitFouls = new int[5];

    private Match actualMatch;

    private MatchViewModel matchViewModel;
    private StatsViewModel statsViewModel;

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public CounterFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        actualMatch = new Match(teamHome, teamVisit, 0, 0);

        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel.class);
        statsViewModel = ViewModelProviders.of(this).get(StatsViewModel.class);



        homeSpinner = view.findViewById(R.id.local_spinner);
        visitSpinner = view.findViewById(R.id.visitante_spinner);
        homePointsView = view.findViewById(R.id.homePoints);
        homeFoulsView = view.findViewById(R.id.homeFouls);
        visitPointsView = view.findViewById(R.id.visitPoints);
        visitFoulsView = view.findViewById(R.id.visitFouls);
        possession = view.findViewById(R.id.possessionTime);
        quarter = view.findViewById(R.id.quarter);
        quarterTime = view.findViewById(R.id.quarterTime);

        final ImageView resetPossession = (ImageView) view.findViewById(R.id.resetPossession);
        ImageView pausePosesion = (ImageView) view.findViewById(R.id.pausePossession);
        ImageView continuePosesion = (ImageView) view.findViewById(R.id.continuePossession);
        ImageView previousQuarter = (ImageView) view.findViewById(R.id.previousQuarter);
        ImageView pauseQuater = (ImageView) view.findViewById(R.id.pauseQuarter);
        ImageView continueQuarter = (ImageView) view.findViewById(R.id.continueQuarter);
        final ImageView nextQuarter = (ImageView) view.findViewById(R.id.nextQuarter);


        fillSpinners();

        homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamHome = teamList.get(position);

                resetCounter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        visitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamVisit = teamList.get(position);

                resetCounter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        resetPossession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!possessionRunning) {
                    resetPossession();
                }

            }
        });

        pausePosesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (possessionRunning) {
                    pausePossession();
                }

            }
        });

        continuePosesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!possessionRunning) {
                    continuePossession();
                }

            }
        });

        previousQuarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!quarterRunning) {
                    previousQuarter();
                }

            }
        });

        pauseQuater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quarterRunning) {
                    pauseQuater();
                }

            }
        });

        continueQuarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!quarterRunning) {
                    continueQuarter();
                }

            }
        });

        nextQuarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!quarterRunning) {
                    nextQuarter();
                }
            }
        });

        updateCountDownQuarter();
        updateCountDownPos();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.counter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.finish_match:
                finishMatch();

                return true;
            case R.id.reset_match:
                resetCounter();
                return true;

        }
        return false;
    }

    private void finishMatch() {

        if (actualMatch.getHomeTeam() == actualMatch.getVisitor()) {
            Toast.makeText(getContext(), "Teams must be different", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        matchViewModel.getMatchCount().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                actualMatch.setId(aLong+1);

            }
        });

        CounterPlayerFragment cpfHome = (CounterPlayerFragment) getChildFragmentManager().findFragmentById(R.id.fragmentLocal);
        CounterPlayerFragment cpfVisit = (CounterPlayerFragment) getChildFragmentManager().findFragmentById(R.id.fragmentVisitante);

        List<Stats> statsHome = cpfHome.getStats();
        List<Stats> statsVisit = cpfVisit.getStats();

        actualMatch.setPointsHT(homePoints);
        actualMatch.setPointsV(visitPoints);

        matchViewModel.addMatch(actualMatch);

        long id = matchViewModel.getIdIntro();

        for (int i = 0; i < statsHome.size(); i++) {
            statsHome.get(i).setMatchId(id);
        }

        for (int i = 0; i < statsVisit.size(); i++) {
            statsVisit.get(i).setMatchId(id);
        }

        statsViewModel.addStats(statsHome);
        statsViewModel.addStats(statsVisit);

        resetCounter();

    }

    private void fillSpinners() {
        teamModel = ViewModelProviders.of(this).get(TeamViewModel.class);
        teamModel.getTeams().observe(this, new androidx.lifecycle.Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                String[] teamNames = new String[teams.size()];

                for (int i = 0; i < teams.size(); i++) {
                    teamNames[i] = teams.get(i).getName();
                }

                ArrayAdapter aa = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, teamNames);
                aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                homeSpinner.setAdapter(aa);
                visitSpinner.setAdapter(aa);

                teamList = teams;

            }
        });
    }

    private void changeHome() {
        actualMatch.setHomeTeam(teamHome);
        //getChildSupportManager porque el fragment está dentro de este
        CounterPlayerFragment cpfHome = (CounterPlayerFragment) getChildFragmentManager().findFragmentById(R.id.fragmentLocal);
        cpfHome.setTeam(teamHome, true);
    }

    private void changeVisit(){
        actualMatch.setVisitor(teamVisit);
        CounterPlayerFragment cpfVisit = (CounterPlayerFragment) getChildFragmentManager().findFragmentById(R.id.fragmentVisitante);
        cpfVisit.setTeam(teamVisit, false);
    }

    private void resetPossession() {
        possessionTemp = Utils.POSSESSION_TIME;
        updateCountDownPos();

    }

    private void continuePossession() {
        possessionCountDown = new CountDownTimer(possessionTemp, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                possessionTemp = millisUntilFinished;
                updateCountDownPos();
            }

            @Override
            public void onFinish() {
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.whistle);

                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

                possessionRunning = false;
            }


        }.start();

        possessionRunning = true;
    }

    private void pausePossession() {
        possessionCountDown.cancel();
        possessionRunning = false;

    }

    private void updateCountDownPos() {
        long sec = (possessionTemp / 1000) % 60;
        possession.setText(String.format("0:%02d", sec));
    }

    private void nextQuarter() {
        if (quarterNumber < 5) {
            quarterTemp = Utils.QUARTER_TIME;
            quarterNumber++;
            updateCountDownQuarter();
            updateCounter();

            if (possessionRunning) {
                pausePossession();
            }
            resetPossession();
        }
    }

    private void continueQuarter() {
        quarterCountDown = new CountDownTimer(quarterTemp, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                quarterTemp = millisUntilFinished;
                updateCountDownQuarter();
            }

            @Override
            public void onFinish() {
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.buzzer);

                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

                quarterRunning = false;
            }
        }.start();

        quarterRunning = true;
    }

    private void pauseQuater() {
        quarterCountDown.cancel();
        quarterRunning = false;
    }

    private void previousQuarter() {
        if (quarterNumber > 1) {
            quarterTemp = Utils.QUARTER_TIME;
            quarterNumber--;
            updateCountDownQuarter();
            updateCounter();

            if (possessionRunning) {
                pausePossession();
            }
            resetPossession();
        }


    }

    private void updateCountDownQuarter() {
        long min = (quarterTemp / 1000) / 60;
        long sec = (quarterTemp / 1000) % 60;
        quarterTime.setText(String.format("%02d:%02d", min, sec));

        switch (quarterNumber) {
            case 1:
                quarter.setText(R.string.first_quarter);
                break;
            case 2:
                quarter.setText(R.string.second_quarter);
                break;
            case 3:
                quarter.setText(R.string.third_quarter);
                break;
            case 4:
                quarter.setText(R.string.fourth_quarter);
                break;
            case 5:
                quarter.setText(R.string.prorogue);
                break;
        }
    }

    public void addPoints(int points, boolean homeVisit) {
        if (homeVisit) {
            homePoints += points;
        } else {
            visitPoints += points;
        }

        updateCounter();
    }

    public void addFoul(boolean homeVisit) {
        if (homeVisit) {
            homeFouls[quarterNumber-1] += 1;
        } else {
            visitFouls[quarterNumber-1] += 1;
        }

        updateCounter();
    }

    public void cancelFoul(boolean homeVisit) {
        if (homeVisit) {
            homeFouls[quarterNumber-1] -= 1;
        } else {
            visitFouls[quarterNumber-1] -= 1;
        }

        updateCounter();
    }

    private void updateCounter() {
        homePointsView.setText(homePoints + "");
        visitPointsView.setText(visitPoints + "");
        homeFoulsView.setText(getResources().getString(R.string.foul, homeFouls[quarterNumber-1]));
        visitFoulsView.setText(getResources().getString(R.string.foul, visitFouls[quarterNumber-1]));
    }

    private void resetCounter() {
        changeHome();
        changeVisit();
        homePoints = 0;
        visitPoints = 0;
        homeFouls = new int[5];
        visitFouls = new int[5];

        updateCounter();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {

            mMediaPlayer.release();

            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }
}
