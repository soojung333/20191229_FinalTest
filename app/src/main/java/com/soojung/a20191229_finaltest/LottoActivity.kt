package com.soojung.a20191229_finaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.*
import kotlin.collections.ArrayList

class LottoActivity : BaseActivity() {

    val winLottoNumArr = ArrayList<Int>()

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
        }


    }


//    당첨번호 만드는 함수를 따로 빼줌
    fun makeWinLottoNum() {

//    6 개의 당첨 번호 생성 => 6번 반복
//    랜덤으로 숫자를 생성 => 아무 제약 없는 랜덤이 아니라 => 1~45 의 범위. / 중복 허용X 제약 조건.
//    제약 조건을 통과 한다면 => 당첨번호 목록으로 추가. (2, 10, 25 ... ) => 배열(ArrayList)을 사용.
//    작은 숫자부터 나타나도록 정렬
//    여기까지 완료되면 6개의 텍스트뷰에 반영.


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

}


    override fun setValues() {
    }


}
