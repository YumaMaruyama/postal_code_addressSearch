package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domail.service.ZipcodeService;
import com.example.demo.model.ZipcodeDTO;

@Controller
public class ZipCodeControllerController {

	@Autowired
	ZipcodeService zipcodeService;

	//郵便番号で住所を検索するフォーム
	@GetMapping("/zipcode")
	public String getZipcodeForm(HttpSession session, Model model) {

		return "zipcode";
	}



	//郵便番号情報を表示
	@PostMapping(value = "/confirm",params = "search")
	public String postZipcodeConfirm(HttpSession session, Model model, @RequestParam("zipcode") String zipcode) {
		System.out.println("住所検索（search）到達");
		System.out.println("zipcodeの中身　　" + zipcode);
		//バリデーションチェックでnull or 空文字の場合にエラーメッセージ
		if (zipcode == null || zipcode.isEmpty()) {
			System.out.println("エラーチェック到達");
			model.addAttribute("result", "正しい郵便番号を入力してください");
			return getZipcodeForm(session, model);
		}

		//郵便番号検索APIサービスの呼び出し
		ZipcodeDTO zipcodedto = zipcodeService.service(zipcode);
		System.out.println("URL   " + zipcodedto);
		model.addAttribute("zipcodeList", zipcodedto.getResults());

		return "zipcode-confirm";

	}

	@PostMapping(value = "/confirm", params = "home")
	public String postConfirmHome(HttpSession session,Model model) {

		return "zipcode";
	}

}
