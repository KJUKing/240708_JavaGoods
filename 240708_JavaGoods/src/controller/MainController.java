package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.GoodsService;
import util.ScanUtil;
import view.Print;
import view.View;
import vo.GoodsVo;

public class MainController extends Print {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	GoodsService goodsService = GoodsService.getInstance();

	public static void main(String[] args) {
		new MainController().start();
	}

	private void start() {
		View view = View.MAIN;
		while (true) {
			switch (view) {
				case MAIN:
					view = main();
					break;
				case GOODS_LIST:
					view = goodsList();
					break;
				case GOODS_DETAIL:
					view = goodsDetail();
					break;
				case GOODS_UPDATE:
					view = goodsUpdate();
					break;
				case GOODS_DELETE:
					view = goodsDelete();
					break;
				case GOODS_INSERT:
					view = goodsInset();
					break;
				default:
					break;
			}
		}
	}

	private View goodsDetail() {
		int goodsNo = (int) sessionStorage.get("goodsNo");
		List<Object> param = new ArrayList<>();
		param.add(goodsNo);
		GoodsVo goods = goodsService.goodsDetail(param);
		detailPrint(goods);
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1: return View.GOODS_UPDATE;
			case 2: return View.GOODS_DELETE;
			case 3: return View.GOODS_LIST;
			default: return View.GOODS_DETAIL;
		}
	}

	private View goodsList() {
		List<GoodsVo> goodsList = null;
		if (sessionStorage.containsKey("search")) {
			List<Object> param = new ArrayList();
			param.add(sessionStorage.remove("search"));
			goodsList = goodsService.goodsList(param);
		}
		else goodsList = goodsService.goodsList();
		listPrint(goodsList);

		int sel = ScanUtil.menu();

		switch (sel) {
			case 1:
				int goodsNo = ScanUtil.nextInt("상품 번호 : ");
				sessionStorage.put("goodsNo", goodsNo);
				return View.GOODS_DETAIL;
			case 2:
				String search = ScanUtil.nextLine("검색 키워드 : ");
				sessionStorage.put("search", search);
				return View.GOODS_LIST;
			case 3: return View.MAIN;
			default: return View.GOODS_DETAIL;
		}
	}

	private View goodsDelete() {
		int no = (int)sessionStorage.get("goodsNo");
		List<Object> param = new ArrayList<>();
		param.add(no);
		goodsService.goodsDelete(param);
		return View.GOODS_LIST;
	}

	private View goodsUpdate() {
		System.out.println("1. 전체 수정");
		System.out.println("2. 게시판 리스트");
		int sel = ScanUtil.menu();
		if (sel == 1) {
			int no = (int)sessionStorage.get("goodsNo");
			List<Object> param = new ArrayList<>();
			String name = ScanUtil.nextLine("이름 : ");
			param.add(name);
			String content = ScanUtil.nextLine("내용 : ");
			param.add(content);
			String type = ScanUtil.nextLine("타입 : ");
			param.add(type);
			int price = ScanUtil.nextInt("가격 : ");
			param.add(price);
			param.add(no);
			goodsService.goodsUpdate(param);

			return View.GOODS_DETAIL;
		}

		if (sel == 2) {
			return View.GOODS_LIST;
		}



		return View.GOODS_DETAIL;
	}

//
	private View goodsInset() {
		List<Object> param = new ArrayList<>();
		String name = ScanUtil.nextLine("이름 : ");

		String content = ScanUtil.nextLine("내용 : ");

		String type = ScanUtil.nextLine("타입 : ");

		int price = ScanUtil.nextInt("가격 : ");

		param.add(name);
		param.add(content);
		param.add(type);
		param.add(price);

		goodsService.goodsInsert(param);

		return View.GOODS_LIST;
	}


	public View main() {

		mainPrint();
		int sel = ScanUtil.nextInt("메뉴 선택: ");
		switch (sel) {
			case 1:
				return View.GOODS_LIST;
			case 2:
				return View.GOODS_INSERT;
			default:
				return View.MAIN;
		}
	}

}
