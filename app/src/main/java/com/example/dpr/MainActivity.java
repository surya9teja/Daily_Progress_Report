package com.example.dpr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    TextView txtDate, txtIssueTime, txtReturnTime, project_manager;
    Spinner spinner;
    Button submit;
    DatePickerDialog datePickerDialog;
    EditText site_engineer, channel_partner, line_name, line_length, route_length, drum_number, location_number, today_work, plan_tomorrow, ehs, remarks;
    String managers[] = new String[]{"Rajendra A", "Rajendra A", "Venkateswara", "Sankar G", "Sankar G", "Sankar G", "Sankar G", "Sankar G", "Sankar G", "Devendra", "Shyama", "Venkateswara", "Rajendra A"};
    String project[] = new String[]{"AP- 1132 & 636km", "AP- 242km", "BSPTCL", "GETCO- 2226", "GETCO- 2274 P1", "GETCO- 2274 P2", "GETCO- 2275", "GETCO- 2276", "GETCO- 2278", "PGCIL-1851km", "PGCIL-721km", "PTCUL", "TS-216km"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/")
                .build();
        final postdata post=retrofit.create(postdata.class);
        spinner = (Spinner) findViewById(R.id.project_list);
        txtDate = (TextView) findViewById(R.id.in_date);
        txtIssueTime = (TextView) findViewById(R.id.in_ptw_issue_time);
        txtReturnTime = (TextView) findViewById(R.id.in_ptw_return_time);
        project_manager = (TextView) findViewById(R.id.in_project_manager);
        site_engineer = (EditText) findViewById(R.id.in_site_engineer);
        channel_partner = (EditText) findViewById(R.id.in_channel_partner);
        line_name = (EditText) findViewById(R.id.in_line_name);
        line_length = (EditText) findViewById(R.id.in_line_length);
        route_length = (EditText) findViewById(R.id.in_route_length);
        drum_number = (EditText) findViewById(R.id.in_drum_number);
        location_number = (EditText) findViewById(R.id.in_location_number);
        today_work = (EditText) findViewById(R.id.in_today_work);
        plan_tomorrow = (EditText) findViewById(R.id.in_plan_tomorrow);
        ehs = (EditText) findViewById(R.id.in_ehs);
        remarks = (EditText) findViewById(R.id.in_remarks);
        submit = (Button) findViewById(R.id.btn_submit);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        txtIssueTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrenttime = Calendar.getInstance();
                int hour = mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrenttime.get(Calendar.MINUTE);
                TimePickerDialog mtimepicker;
                mtimepicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        txtIssueTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                mtimepicker.setTitle("Select Time");
                mtimepicker.show();
            }
        });
        txtReturnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrenttime = Calendar.getInstance();
                int hour = mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrenttime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        txtReturnTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, project);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                project_manager.setText(managers[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Project_Name = spinner.getSelectedItem().toString().trim();
                String Date = txtDate.getText().toString().trim();
                String Project_Manager = project_manager.getText().toString().trim();
                String Site_Engineer = site_engineer.getText().toString().trim();
                String Channel_partner = channel_partner.getText().toString().trim();
                String Line_Name = line_name.getText().toString().trim();
                String Line_Length = line_length.getText().toString().trim();
                String Route_Length = route_length.getText().toString().trim();
                String Drum_Number = drum_number.getText().toString().trim();
                String Location_Number = location_number.getText().toString().trim();
                String PTW_Issue_Time = txtIssueTime.getText().toString().trim();
                String PTW_Return_Time = txtReturnTime.getText().toString().trim();
                String Work_Today = today_work.getText().toString().trim();
                String Plan_Tomorrow = plan_tomorrow.getText().toString().trim();
                String EHS = ehs.getText().toString().trim();
                String Remarks = remarks.getText().toString().trim();
                Call<Void> completeQuestionnaireCall=post.completeQuestionnaire(Project_Name,Date,Project_Manager,Site_Engineer,Channel_partner
                ,Line_Name,Line_Length,Route_Length,Drum_Number,Location_Number,PTW_Issue_Time,PTW_Return_Time,Work_Today,Plan_Tomorrow,EHS,Remarks);
                completeQuestionnaireCall.enqueue(callCallback);
            }
        });

    }
    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }
    };

}


