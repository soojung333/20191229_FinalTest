package com.soojung.a20191229_finaltest

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.*
import kotlin.collections.ArrayList

class LottoActivity : BaseActivity() {

    var totalWinMoney = 0L     //  0 을 Long 타입으로 대입 => 그냥 0 은 int로 간주되어, 50억 같은 큰 금액 담지 못함
    var usedMoney = 0L

    val winLottoNumArr = ArrayList<Int>()
    val winLottoNumTextViewList = ArrayList<TextView>()
    val myLottoNumTextViewList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        buyOneLottoBtn.setOnClickListener {

//            6 개의 숫자를 랜덤으로 생성 -> 텍스트뷰 6 개에 반영
            makeWinLottoNum()

//            몇등인지 판단하기
            checkLottoRank()
        }
    }

    fun checkLottoRank() {

//        등 수 판단?
//        내가 가진 숫자들과 / 당첨번호를 하나하나 비교해서, 같은 숫자가 몇 개인지? 세어야 함.
//        이 갯수에 따라서 등수를 판정.
//        6개 : 1등 / 5개 : 3등 / 4개 : 4등 / 3개 : 5등

//        같은 숫자의 갯수를 세어주는 변수
        var correctCount = 0

//        내가 가진 숫자들을 모두 꺼내보자.
//        총 몇 개의 숫자를 맞췄는지 correctCount 에 저장.
        for (myNumTxt in myLottoNumTextViewList) {
//            각 텍스트뷰에 적힌 숫자가 String 형태 => Int로 변환
            val num = myNumTxt.text.toString().toInt()

            Log.d("적혀있는 숫자들", num.toString())

//            당첨 번호를 둘러보자
            for (winNum in winLottoNumArr) {

//                같은 숫자를 찾았다면
                if (num == winNum) {

//                    당첨번호에 들어있다! 맞춘 갯수 1 증가
                    correctCount++
                    break
                }
            }

        }

//        맞춘 갯수에 따라 등수 판정.

        if (correctCount == 6) {
//            1등 당첨! => 당첨금액 += 50억
            totalWinMoney += 5000000000
        }
        else if (correctCount == 5) {
//            3등 당첨! => 당첨금액 += 150만원
            totalWinMoney += 1500000
        }
        else if (correctCount == 4) {
//            3등 당첨! => 당첨금액 += 5만원
            totalWinMoney += 50000
        }
        else if (correctCount == 3) {
//            3등 당첨! => 당첨금액 +=  5천원
            totalWinMoney += 5000
        }
        else  {
//            꽝 ! => 당첨금액 변화 없음
        }

        totalWinMoneyTxt.text = totalWinMoney.toString()

//        사용금액 : 한장 살 때마다 천원씩 증가.
        usedMoney += 1000
        usedMoneyTxt.text = usedMoney.toString()

    }



//    당첨번호 만드는 함수를 따로 빼줌
    fun makeWinLottoNum() {

//    기존의 당첨번호를 싸그리 삭제.
//    6 개의 당첨 번호 생성 => 6번 반복
//    랜덤으로 숫자를 생성 => 아무 제약 없는 랜덤이 아니라 => 1~45 의 범위. / 중복 허용X 제약 조건.
//    제약 조건을 통과 한다면 => 당첨번호 목록으로 추가. (2, 10, 25 ... ) => 배열(ArrayList)을 사용.
//    작은 숫자부터 나타나도록 정렬
//    여기까지 완료되면 6개의 텍스트뷰에 반영.

    winLottoNumArr.clear()

    for (i in 0..5) {
        while (true) {
            val randomInt = Random().nextInt(45) + 1    // 0~44 의 랜덤값에 +1을 해주면 1~45 가 됨

            var isDupOk = true   // 중복 검사를 통과한다고 먼저 전제.

            for (num in winLottoNumArr) {
                if (randomInt == num) {
//                이미 뽑아둔 당첨 번호와 랜덤으로 뽑은 번호가 같다. => 이미 나온 번호 ! 중복이다 !
//                중복검사 통과 X
                    isDupOk = false
                    break
                }
            }

            if (isDupOk) {       //  (isDupOk == true) 를 생략가능
                winLottoNumArr.add(randomInt)
                break
            }

        }

    }

//    Collections 클래스의 기능을 이용해서 ArrayList 내부의 값을 정렬 기능 이용.
    Collections.sort(winLottoNumArr)


//    6 개의 당첨번호 / 각 자리의 텍스트뷰를 매칭 시켜주는 for문
    for (i in 0..5) {
//        상황에 맞는 텍스트뷰 / 당첨번호 뽑아와서
        val tempTextView = winLottoNumTextViewList.get(i)
        val winNum = winLottoNumArr.get(i)

//        값으로 세팅
        tempTextView.text = winNum.toString()
    }


}


    override fun setValues() {

//        당첨번호 텍스트뷰 배열로 담아둠
        winLottoNumTextViewList.add(lottoNumTxt01)
        winLottoNumTextViewList.add(lottoNumTxt02)
        winLottoNumTextViewList.add(lottoNumTxt03)
        winLottoNumTextViewList.add(lottoNumTxt04)
        winLottoNumTextViewList.add(lottoNumTxt05)
        winLottoNumTextViewList.add(lottoNumTxt06)

//        내가 뽑은 번호 텍스트뷰들을 배열로 담아둠
        myLottoNumTextViewList.add(myNumTxt01)
        myLottoNumTextViewList.add(myNumTxt02)
        myLottoNumTextViewList.add(myNumTxt03)
        myLottoNumTextViewList.add(myNumTxt04)
        myLottoNumTextViewList.add(myNumTxt05)
        myLottoNumTextViewList.add(myNumTxt06)

    }


}
