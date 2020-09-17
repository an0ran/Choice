package kr.nodeline.choice.scenarios.join.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.join_karaoke_step3_fragment.*
import kotlinx.android.synthetic.main.join_karaoke_step4_fragment.*

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.commons.utils.hideKeyboard
import kr.nodeline.choice.databinding.JoinKaraokeStep3FragmentBinding
import kr.nodeline.choice.databinding.JoinKaraokeStep4FragmentBinding
import kr.nodeline.choice.scenarios.join.viewmodel.JoinViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File


class JoinKaraokeStep4Fragment : BaseKotlinFragment<JoinKaraokeStep4FragmentBinding, JoinViewModel>() {

    private val TAG = "JoinKaraokeStep4Fragment"

    override val layoutResourceId: Int
        get() = R.layout.join_karaoke_step4_fragment
    override val viewModel: JoinViewModel by viewModel()

    var loginId = ""
    var loginPw = ""
    var karaokeName = ""
    var karaokeTel = ""
    var karaokeAddress = ""
    var bugiImgBody: MultipartBody.Part? = null

    var isAttachBusi = false

    override fun initStartView() {

        checkPermission()

        et_join_karaoke_step4_owner_name.textChanges()
            .subscribe {
                updateDoneButton()
            }

        et_join_karaoke_step4_owner_phone.textChanges()
            .subscribe {
                updateDoneButton()
            }

        et_join_karaoke_step4_busiNum.textChanges()
            .subscribe {
                updateDoneButton()
            }

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

        view!!.setOnClickListener {
            view!!.hideKeyboard()
        }

        btn_join_karaoke_step4_back.setOnClickListener {
            fragmentManager!!.popBackStack()
        }

        btn_join_karaoke_step4_attach_busi.setOnClickListener {
            checkPermission()
        }

        btn_join_karaoke_step4_done.setOnClickListener {
            val ownerName = et_join_karaoke_step4_owner_name.text.toString()
            val ownerPhone = et_join_karaoke_step4_owner_phone.text.toString()
            val busiNum = et_join_karaoke_step4_busiNum.text.toString()
            if (!ownerName.isNullOrEmpty() && !ownerPhone.isNullOrEmpty() && !busiNum.isNullOrEmpty() && isAttachBusi) {

            }

        }

    }

    private fun updateDoneButton() {
        if (!et_join_karaoke_step4_owner_name.text.isNullOrEmpty() && !et_join_karaoke_step4_owner_phone.text.isNullOrEmpty()
            && !et_join_karaoke_step4_busiNum.text.isNullOrEmpty() && isAttachBusi) {
            btn_join_karaoke_step4_done.setBackgroundColor(Color.parseColor("#FF18244F"))
            btn_join_karaoke_step4_done.isEnabled = true
        } else {
            btn_join_karaoke_step4_done.setBackgroundColor(Color.parseColor("#3618244F"))
            btn_join_karaoke_step4_done.isEnabled = false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            for (grantResult in grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent()
                    intent.type = MediaStore.Images.Media.CONTENT_TYPE
                    intent.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(intent, 101)
                }
            }
        }
    }

    fun checkPermission() {
        var temp = ""
        if (ContextCompat.checkSelfPermission(context!!,
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " "
        }
        if (ContextCompat.checkSelfPermission(context!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " "
        }
        if (!TextUtils.isEmpty(temp)) {
            ActivityCompat.requestPermissions(activity!!, temp.trim().split(" ").toTypedArray(), 1)
        } else {
            val intent = Intent()
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) return

        if (requestCode == 101) {
            var uri = data?.data
            if (uri != null) {
                iv_join_karaoke_step4_busi.isGone = false
                Glide.with(this).load(uri).into(iv_join_karaoke_step4_busi)
                var file = File(getRealPathFromUrl(uri))
                var requestBody = RequestBody.create(MediaType.parse("image/*"), file)
                this.bugiImgBody = MultipartBody.Part.createFormData("busiImg", file.name, requestBody)
                this.isAttachBusi = true
                updateDoneButton()
            }
        }
    }

    private fun getRealPathFromUrl(uri: Uri): String {
        var result: String
        var cursor = context!!.contentResolver.query(uri, null, null, null, null)
        if (cursor == null) {
            result = uri.path.toString()
        } else {
            cursor.moveToFirst()
            var idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

}
