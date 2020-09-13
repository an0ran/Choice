package kr.nodeline.choice.commons.di

import kr.nodeline.choice.scenarios.choice.model.ChoiceModel
import kr.nodeline.choice.scenarios.choice.model.ChoiceModelImpl
import kr.nodeline.choice.scenarios.choice.viewmodel.ChoiceViewModel
import kr.nodeline.choice.scenarios.karaoke.model.KaraokeModel
import kr.nodeline.choice.scenarios.karaoke.model.KaraokeModelImpl
import kr.nodeline.choice.scenarios.karaoke.viewmodel.KaraokeViewModel
import kr.nodeline.choice.scenarios.login.model.LoginModel
import kr.nodeline.choice.scenarios.login.model.LoginModelImpl
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import kr.nodeline.choice.scenarios.payment.model.PaymentModel
import kr.nodeline.choice.scenarios.payment.model.PaymentModelImpl
import kr.nodeline.choice.scenarios.payment.viewmodel.PaymentViewModel
import kr.nodeline.choice.scenarios.profile.model.ProfileModel
import kr.nodeline.choice.scenarios.profile.model.ProfileModelImpl
import kr.nodeline.choice.scenarios.profile.viewmodel.ProfileViewModel
import kr.nodeline.choice.scenarios.setting.model.SettingModel
import kr.nodeline.choice.scenarios.setting.model.SettingModelImpl
import kr.nodeline.choice.scenarios.setting.viewmodel.SettingViewModel
import kr.nodeline.choice.scenarios.sidemenu.model.SideMenuModel
import kr.nodeline.choice.scenarios.sidemenu.model.SideMenuModelImpl
import kr.nodeline.choice.scenarios.sidemenu.viewmodel.SideMenuViewModel
import kr.nodeline.choice.scenarios.singer.model.SingerModel
import kr.nodeline.choice.scenarios.singer.model.SingerModelImpl
import kr.nodeline.choice.scenarios.singer.viewmodel.SingerViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

var adapterPart = module {

}

var modelPart = module {
    factory<LoginModel> {
        LoginModelImpl()
    }
    factory<SingerModel> {
        SingerModelImpl()
    }
    factory<KaraokeModel> {
        KaraokeModelImpl()
    }
    factory<ChoiceModel> {
        ChoiceModelImpl()
    }
    factory<PaymentModel> {
        PaymentModelImpl()
    }
    factory<SettingModel> {
        SettingModelImpl()
    }
    factory<SideMenuModel> {
        SideMenuModelImpl()
    }
    factory<ProfileModel> {
        ProfileModelImpl()
    }
}

var viewModelPart = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        SingerViewModel(get())
    }
    viewModel {
        KaraokeViewModel(get())
    }
    viewModel {
        ChoiceViewModel(get())
    }
    viewModel {
        PaymentViewModel(get())
    }
    viewModel {
        SettingViewModel(get())
    }
    viewModel {
        SideMenuViewModel(get())
    }
    viewModel {
        ProfileViewModel(get())
    }

}

var myDiModule = listOf(adapterPart, modelPart, viewModelPart)