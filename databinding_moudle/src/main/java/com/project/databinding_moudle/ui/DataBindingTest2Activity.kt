package com.project.databinding_moudle.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.*
import com.project.baselib.ui.BaseActivity
import com.project.databinding_moudle.R
import com.project.databinding_moudle.data.Person
import com.project.databinding_moudle.data.Student
import com.project.databinding_moudle.data.Teacher
import com.project.databinding_moudle.data.User
import com.project.databinding_moudle.databinding.ActivityDataBindingTest2Binding
import com.project.databinding_moudle.databinding.LayoutTestBinding

class DataBindingTest2Activity : BaseActivity<ActivityDataBindingTest2Binding>() {

    private val person = Person()
    private val student: Student = Student()
    private lateinit var mLayoutTestBinding: LayoutTestBinding

    override fun getLayoutId(): Int = R.layout.activity_data_binding_test2

    override fun initUi() {
//        val binding = DataBindingUtil.inflate<LayoutTestBinding>(LayoutInflater.from(this),R.layout.layout_test,null,false)
//        val params = ConstraintLayout.LayoutParams(0,ConstraintLayout.LayoutParams.WRAP_CONTENT)
//        params.topToBottom = R.id.tv_content4
//        params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
//        params.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
//        val view = binding.root
//        view.layoutParams = params
//        (mBinding.root as ViewGroup).addView(view)
        mBinding.vsTest.setOnInflateListener{
            stub,view ->
                mLayoutTestBinding = DataBindingUtil.bind<LayoutTestBinding>(view)!!
                mLayoutTestBinding.user = User("赵六",20,"2324")
        }
    }

    override fun initData() {
        mBinding.person = person
        person.firstName.set("Bob")
        person.lastName.set("Smith")
        person.age.set(10)
        person.height.set(178.0f)
        person.isBoy.set(true)

        val map = ObservableArrayMap<String,Any>().apply {
            put("lastName","三")
            put("age",17)

        }
        mBinding.userMap = map
        map["firstName"] = "张"

        val array = ObservableArrayList<Any>().apply {
            add("李")
            add("四")
            add(15)
        }
        mBinding.userArray = array



        mBinding.student = student
        student.name = "小明"
        student.age = 20

        val teacher = Teacher()
        mBinding.teacher = teacher
        teacher.name = "王五"
        teacher.course = "体育"
    }

    override fun doClick(view: View?) {
        super.doClick(view)
        if(view?.id == R.id.btn_change_data){
            person.age.set(20)
            person.height.set(188.0f)
        }else if(view?.id == R.id.btn_change_student_data){
            student.name = "小红"
            student.age = 12
        }else if(view?.id == R.id.btn_load_view_stub){
            if(!mBinding.vsTest.isInflated)
                mBinding.vsTest.viewStub?.inflate()
        }

    }
}
