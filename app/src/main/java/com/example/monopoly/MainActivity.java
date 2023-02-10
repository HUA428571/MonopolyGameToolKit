package com.example.monopoly;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.view.KeyEvent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

	private int player_1_money;
	private int player_2_money;
	private int player_3_money;
	private int player_4_money;
	Button btn_player_1, btn_player_2, btn_player_3, btn_player_4;
	Button btn_go, btn_destiny, btn_transfer, btn_confirm, btn_bank;
	EditText edit_moneyTransfer;
	TextView text_money_player_1, text_money_player_2, text_money_player_3, text_money_player_4;
	TextView text_step_player_1, text_step_player_2, text_step_player_3, text_step_player_4;
	TextView text_log;

	int flag_transfer = 0;
	int flag_transfer_in = 0;
	int transfer_out = 0;
	int transfer_in = 0;
//	int flag_transfer_out=0;
//	int flag_transfer_out=0;


	String[] destiny = new String[]{
			"华尔街投资获利，未来三回合灰色地区租金翻倍",
			"加入巴西足球队，未来三回合绿色地区租金翻倍",
			"新冠靶向药研发成功，未来三回合所有地区租金翻倍",
			"全球脱贫，未来三回合1000$以下地区租金翻倍",
			"全球第10086次大战爆发，未来三回合土黄色地区租金翻倍减半",
			"红色玩家与前男友分手复合10次，未来三回合红色玩家地区租金翻倍",
			"绿色玩家uno获得第一，未来三回合绿色玩家地区租金翻倍",
			"蓝色玩家去海底捞过生日，未来三回合蓝色玩家地区租金翻倍",
			"轮船动力即将耗尽，移动到最近的空地并购买",
			"大力征收房产税，每位玩家每栋房子支付500$",
			"发生地震，摧毁最近的一幢房子",
			"中国4男足退出亚洲前十行列，未来三回合红色地区租金减半",
			"轮船触礁，暂停一回合并支付1000修缮费",
			"夸父追日喝光了所有水源，未来三回合所有海域收取1000水费",
			"复联在美国对战洛基，美国下回合无法收租",
			"天临元年，卢雷变法，支付5%论文查重费",
			"遇见龙卷风，倒退六格，下三回合转向",
			"去泰国变性成为花魁，未来三回合蓝色地区租金翻倍",
			"黄色玩家赌博获利，未来三回合黄色玩家地区租金翻倍",
			"3D打印造房技术成熟，所有玩家投一个骰子造对应数量房子",
			"一路向北，到达北极并冻伤一回合",
			"袋鼠大量繁殖，澳大利亚所有者支付1000",
			"安倍晋三遇刺，每位玩家投骰子，点数最大的获得日本所有权",
			"意外获得快艇，未来三回合步数翻倍",
			"找珠江路大叔买了只乌龟，未来三回合前进一步",
			"高考填错志愿，获得五道口职业技术学院2000奖学金",
			"省长视察扶贫进度，最富有玩家给最贫穷玩家1000",
			"政府拆迁，拆除成本最低的一幢房并获得两幢可自由分配的房",
			"成为海盗，从每位玩家手中窃取1000和一块地（含房子），并坐牢三回合（去北极）",
			"在海中迷失，再抽一张",
			"教幼儿园小朋友雅思托福，获得2000报酬",
			"代刷网课，获利3000并被捕坐牢一回合",
			"成为人大代表，获得一张免罪卡（价值1000）",
			"意外结识杰克船长，杰克船长炮轰其他玩家，其他玩家入狱两回合",
			"买彩票中奖，获利2000",
			"在1912偶遇“中纪委”，被骗2000",
			"贪污受贿被双开，罚款1000，下三回合只能掷骰子和付钱",
			"点外卖超时送达，准时宝赔付获得2000",
			"找到海贼王的宝藏，获得3000",
			"打劫银行并成功逃脱，获得5000",
			"观看300秒广告，停留三回合并获得三张骰子卡（指定任意点数）",
			"进入百慕大三角，所有玩家回到起点并获得3000"};


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//隐藏标题栏
		getSupportActionBar().hide();

		//初始化金额
		player_1_money = player_2_money = player_3_money = player_4_money = 100000;

		//初始化控件
		initView();


	}

	private void initView()
	{
		text_money_player_1 = findViewById(R.id.Text_Money_Player_1);
		text_money_player_2 = findViewById(R.id.Text_Money_Player_2);
		text_money_player_3 = findViewById(R.id.Text_Money_Player_3);
		text_money_player_4 = findViewById(R.id.Text_Money_Player_4);
		text_step_player_1 = findViewById(R.id.Text_Step_Player_1);
		text_step_player_2 = findViewById(R.id.Text_Step_Player_2);
		text_step_player_3 = findViewById(R.id.Text_Step_Player_3);
		text_step_player_4 = findViewById(R.id.Text_Step_Player_4);
		text_log = findViewById(R.id.Text_Log);

		edit_moneyTransfer = findViewById(R.id.Edit_Money);
		edit_moneyTransfer.setOnEditorActionListener(new TextView.OnEditorActionListener()
		{
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
			{
				btn_confirm.callOnClick();
				return false;
			}
		});

		btn_player_1 = (Button) findViewById(R.id.Btn_Player_1);
		btn_player_2 = (Button) findViewById(R.id.Btn_Player_2);
		btn_player_3 = (Button) findViewById(R.id.Btn_Player_3);
		btn_player_4 = (Button) findViewById(R.id.Btn_Player_4);
		btn_player_1.setOnClickListener(this);
		btn_player_2.setOnClickListener(this);
		btn_player_3.setOnClickListener(this);
		btn_player_4.setOnClickListener(this);

		btn_go = (Button) findViewById(R.id.Btn_Go);
		btn_destiny = (Button) findViewById(R.id.Btn_Destiny);
		btn_transfer = (Button) findViewById(R.id.Btn_Transfor);
		btn_confirm = (Button) findViewById(R.id.Btn_Confirm);
		btn_bank = (Button) findViewById(R.id.Btn_Bank);
		btn_go.setOnClickListener(this);
		btn_destiny.setOnClickListener(this);
		btn_transfer.setOnClickListener(this);
		btn_confirm.setOnClickListener(this);
		btn_bank.setOnClickListener(this);

		text_money_player_1.setText(Integer.toString(player_1_money));
		text_money_player_2.setText(Integer.toString(player_2_money));
		text_money_player_3.setText(Integer.toString(player_3_money));
		text_money_player_4.setText(Integer.toString(player_4_money));

	}

	@SuppressLint("SetTextI18n")
	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			//掷骰子
			case R.id.Btn_Go:
				text_step_player_1.setText(Integer.toString((int) (Math.random() * 11) + 1));
				text_step_player_2.setText(Integer.toString((int) (Math.random() * 11) + 1));
				text_step_player_3.setText(Integer.toString((int) (Math.random() * 11) + 1));
				text_step_player_4.setText(Integer.toString((int) (Math.random() * 11) + 1));
				break;
			case R.id.Btn_Transfor:
				//flag_transfer的含义：
				//0:当前没有转账操作
				//1:等待选择转出方
				//2:等待选择转出方
				//3:等待输入转账金额
				//4:转账完成

				switch (flag_transfer)
				{
					case 0:
						text_log.setText("转账：选择转出方");
						flag_transfer = 1;
						break;
					case 1:
						switch (transfer_out)
						{
							case 0:
								text_log.setText("转账：选择转出方");
								break;
							case 1:
								text_log.setText("转出：一号玩家\n请选择转入方");
								flag_transfer = 2;
								break;
							case 2:
								text_log.setText("转出：二号玩家\n请选择转入方");
								flag_transfer = 2;
								break;
							case 3:
								text_log.setText("转出：三号玩家\n请选择转入方");
								flag_transfer = 2;
								break;
							case 4:
								text_log.setText("转出：四号玩家\n请选择转入方");
								flag_transfer = 2;
								break;
							case 5:
								text_log.setText("转出：银行\n请选择转入方");
								flag_transfer = 2;
								break;
						}
						break;
					case 2:
						switch (transfer_in)
						{
							case 0:
								text_log.setText("转账：选择转入方");
								break;
							case 1:
								text_log.setText("转入：一号玩家\n请输入转账金额");
							flag_transfer = 3;
								break;
							case 2:
								text_log.setText("转入：二号玩家\n请输入转账金额");
							flag_transfer = 3;
								break;
							case 3:
								text_log.setText("转入：三号玩家\n请输入转账金额");
							flag_transfer = 3;
								break;
							case 4:
								text_log.setText("转入：四号玩家\n请输入转账金额");
							flag_transfer = 3;
								break;
							case 5:
								text_log.setText("转入：银行\n请输入转账金额");
							flag_transfer = 3;
								break;
						}
						break;
					case 4:
						int money = Integer.parseInt(edit_moneyTransfer.getText().toString());
						String text = "转账：";
						switch (transfer_out)
						{
							case 1:
								player_1_money -= money;
								text = "一号玩家  ->  ";
								break;
							case 2:
								player_2_money -= money;
								text = "二号玩家  ->  ";
								break;
							case 3:
								player_3_money -= money;
								text = "三号玩家  ->  ";
								break;
							case 4:
								player_4_money -= money;
								text = "四号玩家  ->  ";
								break;
							case 5:
								text = "银行  ->  u-";
								break;
						}
						switch (transfer_in)
						{
							case 1:
								player_1_money += money;
								text_log.setText(text + "一号玩家：" + money);
								break;
							case 2:
								player_2_money += money;
								text_log.setText(text + "二号玩家：" + money);
								break;
							case 3:
								player_3_money += money;
								text_log.setText(text + "三号玩家：" + money);
								break;
							case 4:
								player_4_money += money;
								text_log.setText(text + "四号玩家：" + money);
								break;
							case 5:
								text_log.setText(text + "银行：" + money);
								break;
						}
						flag_transfer = 0;
						transfer_in = 0;
						transfer_out = 0;
						text_money_player_1.setText(Integer.toString(player_1_money));
						text_money_player_2.setText(Integer.toString(player_2_money));
						text_money_player_3.setText(Integer.toString(player_3_money));
						text_money_player_4.setText(Integer.toString(player_4_money));
						break;
				}
				break;
			case R.id.Btn_Destiny:
				int destiny_number = (int) (Math.random() * 42);
				text_log.setText(destiny[destiny_number]);
				break;
			case R.id.Btn_Player_1:
				switch (flag_transfer)
				{
					case 1:
						transfer_out = 1;
//						flag_transfer = 2;
						btn_transfer.callOnClick();
						break;
					case 2:
						transfer_in = 1;
						btn_transfer.callOnClick();
						break;
				}
				break;
			case R.id.Btn_Player_2:
				switch (flag_transfer)
				{
					case 1:
						transfer_out = 2;
//						flag_transfer = 2;
						btn_transfer.callOnClick();
						break;
					case 2:
						transfer_in = 2;
						btn_transfer.callOnClick();
						break;
				}
				break;
			case R.id.Btn_Player_3:
				switch (flag_transfer)
				{
					case 1:
						transfer_out = 3;
//						flag_transfer = 2;
						btn_transfer.callOnClick();
						break;
					case 2:
						transfer_in = 3;
						btn_transfer.callOnClick();
						break;
				}
				break;
			case R.id.Btn_Player_4:
				switch (flag_transfer)
				{
					case 1:
						transfer_out = 4;
//						flag_transfer = 2;
						btn_transfer.callOnClick();
						break;
					case 2:
						transfer_in = 4;
						btn_transfer.callOnClick();
						break;
				}
				break;
			case R.id.Btn_Bank:
				switch (flag_transfer)
				{
					case 1:
						transfer_out = 5;
//						flag_transfer = 2;
						btn_transfer.callOnClick();
						break;
					case 2:
						transfer_in = 5;
						btn_transfer.callOnClick();
						break;
				}
				break;
			case R.id.Btn_Confirm:
				switch (flag_transfer)
				{
					case 3:
						flag_transfer = 4;
						btn_transfer.callOnClick();
						break;
				}
				break;
		}
	}
}