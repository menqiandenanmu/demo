// 
function area() {
	this.xxxCountry;	//国家
	this.xxxProvince;	//省
	this.xxxCity;		//市
	this.xxxCounty;		//县
	this.xxxValueOcx;
	this.xxxtype = -1;
	this.xxxdefaultvalue = -1;
	this.areaName ="";
	// 得到值
	this.getValue = function() {
		var CountryValue = this.xxxCountry.value;
		var ProvinceValue = this.xxxProvince.value;
		var CityValue = this.xxxCity.value;
		var CountyValue = this.xxxCounty.value;
		if (CountyValue.length > 9) {
			return CountyValue;
		}
		if (CityValue.length > 6) {
			return CityValue;
		}
		if (ProvinceValue.length > 3) {
			return ProvinceValue;
		}
		if (CountryValue.length == 3)
			return CountryValue;
		return "";
	}
	this.getValue2 = function() {
		var ProvinceValue = this.xxxProvince.value;
		var CityValue = this.xxxCity.value;
		var CountyValue = this.xxxCounty.value;
		if (CountyValue.length > 9) {
			return CountyValue;
		}
		if (CityValue.length > 6) {
			return CityValue;
		}
		if (ProvinceValue.length > 3) {
			return ProvinceValue;
		}
		return "";
	}
	// 设置默认值
	this.setValue = function(value) {
		if (value.length >= 3) {
			this.xxxCountry.value = value.substring(0, 3);
			this.xxxCountry.onchange();
		}
		if (value.length >= 6) {
			this.xxxProvince.value = value.substring(0, 6);
			this.xxxProvince.onchange();
		}
		if (value.length >= 9) {
			this.xxxCity.value = value.substring(0, 9);
			this.xxxCity.onchange();
		}
		if (value.length >=12) {
			this.CountyValue.value = value.substring(0, 12);
			this.CountyValue.onchange();
		}
	}

	this.setValue2 = function(value) {
		if (value.length >= 6) {
			this.xxxProvince.value = value.substring(0, 6);
			this.xxxProvince.onchange();
		}
		if (value.length >= 9) {
			this.xxxCity.value = value.substring(0, 9);
			this.xxxCity.onchange();
		}
		if (value.length >=12) {
			this.CountyValue.value = value.substring(0, 12);
			this.CountyValue.onchange();
		}
	}
	this.setupById = function(countryId,provinceId,cityId,countyId,valueId,type,defaultValue){
		var countryOcx = 	document.getElementById(countryId);
		var provinceOcx = 	document.getElementById(provinceId);
		var cityOcx = 	document.getElementById(cityId);
		var countyOcx = 	document.getElementById(countyId);

		var valueOcx = 	document.getElementById(valueId);		//初始化数据来源

		this.setupOcx(countryOcx,provinceOcx,cityOcx,countyOcx,valueOcx,type,defaultValue);
	}

	this.setupById2 = function(provinceId,cityId,countyId,valueId,type,defaultValue){
		var provinceOcx = 	document.getElementById(provinceId);
		var cityOcx = 	document.getElementById(cityId);
		var countyOcx = 	document.getElementById(countyId);
		var valueOcx = 	document.getElementById(valueId);		//初始化数据来源
		this.setupOcx2(provinceOcx,cityOcx,countyOcx,valueOcx,type,defaultValue);
	}
	// 装载全部控件
	this.setupOcx = function(Country, Province, City,County, ValueOcx, Type,
			Defultvalue) {
		Country._clazz=this;
		Province._clazz=this;
		City._clazz=this;
		County._clazz=this;
		this.xxxCountry = Country;
		this.xxxProvince = Province;
		this.xxxCity = City;
		this.xxxCounty = County;
		this.xxxtype = Type;
		this.xxxdefaultvalue = Defultvalue;
		this.xxxValueOcx = ValueOcx;
		ValueOcx.value = Defultvalue;
		this.setupCountry(this.xxxCountry, "001");
		this.setupProvince(this.xxxProvince, this.xxxdefaultvalue, "001", this.xxxtype);
		this.setupCity(this.xxxCity, this.xxxdefaultvalue, this.xxxdefaultvalue, this.xxxtype);
		this.setupCounty(this.xxxCounty,this.xxxdefaultvalue,this.xxxdefaultvalue,this.xxxtype);
		this.xxxCountry.onchange = function() {
			var countryCode = this.value;
			var clazz=this._clazz;
			clazz.setupProvince(clazz.xxxProvince, countryCode, countryCode,clazz.xxxtype);
			clazz.setupCity(clazz.xxxCity, -1,-1,clazz.xxxtype);
			clazz.setupCounty(clazz.xxxCounty, -1, -1, clazz.xxxtype);						
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		};
		this.xxxProvince.onchange = function() {
			var provinceCode = this.value;
			var clazz=this._clazz;
			clazz.setupCity(clazz.xxxCity, provinceCode, provinceCode, clazz.xxxtype);
			clazz.setupCounty(clazz.xxxCounty, -1, -1, clazz.xxxtype);				
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		};
		this.xxxCity.onchange = function() {
			var cityCode = this.value;
			var clazz=this._clazz;
			clazz.setupCounty(clazz.xxxCounty, cityCode, cityCode, clazz.xxxtype);			
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		}
		this.xxxCounty.onchange = function() {
			var clazz=this._clazz;
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		}
		
	}

	// 装载全部控件
	this.setupOcx2 = function(Province, City,County, ValueOcx, Type, Defultvalue) {
		Province._clazz=this;
		City._clazz=this;
		County._clazz=this;
		this.xxxProvince = Province;
		this.xxxCity = City;
		this.xxxCounty = County;
		this.xxxtype = Type;
		this.xxxdefaultvalue = Defultvalue;
		this.xxxValueOcx = ValueOcx;
		ValueOcx.value = Defultvalue;
		this.setupProvince(this.xxxProvince, this.xxxdefaultvalue, "001", this.xxxtype);
		this.setupCity(this.xxxCity, this.xxxdefaultvalue, this.xxxdefaultvalue, this.xxxtype);
		this.setupCounty(this.xxxCounty, this.xxxdefaultvalue, this.xxxdefaultvalue, this.xxxtype);
		this.xxxProvince.onchange = function() {
			var provinceCode = this.value;
			var clazz=this._clazz;
			clazz.setupCity(clazz.xxxCity, provinceCode, provinceCode, clazz.xxxtype);
			clazz.setupCounty(clazz.xxxCounty, -1, -1, clazz.xxxtype);				
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		};
		this.xxxCity.onchange = function() {
			var cityCode = this.value;
			var clazz=this._clazz;
			clazz.setupCounty(clazz.xxxCounty, cityCode, cityCode, clazz.xxxtype);			
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		}
		this.xxxCounty.onchange = function() {
			var clazz=this._clazz;
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		}
	}

	// 装载全部控件
	this.setupOcx_Query = function(Country, Province, City, County, ValueOcx,
			Defultvalue) {
		Country._clazz=this;
		Province._clazz=this;
		City._clazz=this;
		County._clazz=this;
		this.xxxCountry = Country;
		this.xxxProvince = Province;
		this.xxxCity = City;
		this.xxxCounty= County;
		this.xxxtype = "0";
		this.xxxdefaultvalue = Defultvalue;
		this.xxxValueOcx = ValueOcx;
		this.setupCountry_Query(this.xxxCountry, "0", this.xxxtype);
		this.setupProvince(this.xxxProvince, 0, "0", this.xxxtype);
		this.setupCity(this.xxxCity, 0, 0, this.xxxtype);
		this.xxxCountry.onchange = function() {
			var countryCode = this.value;
			var clazz=this._clazz;
			clazz.setupProvince(clazz.xxxProvince, countryCode, countryCode, clazz.xxxtype);
			clazz.setupCity(clazz.xxxCity, countryCode, countryCode, clazz.xxxtype);
			clazz.setupCounty(clazz.xxxCity, countryCode, countryCode, clazz.xxxtype);
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
			clazz.xxxProvince.onchange();
		};
		this.xxxProvince.onchange = function() {
			var provinceCode = this.value;
			var clazz=this._clazz;
			clazz.setupCity(clazz.xxxCity, provinceCode, provinceCode, clazz.xxxtype);
			clazz.setupCounty(clazz.xxxCity, provinceCode, provinceCode, clazz.xxxtype);			
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		};
		this.xxxCity.onchange = function() {
			var cityCode = this.Value;
			var clazz=this._clazz;
			clazz.setupCounty(clazz.xxxCity, cityCode, cityCode, clazz.xxxtype);			
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		}
		this.xxxCounty.onchange = function() {
			var clazz=this._clazz;
			if (clazz.xxxValueOcx != null) {
				clazz.xxxValueOcx.value = clazz.getValue();
			}
		}		
	}

	// 装载城市
	this.setupCounty = function(county, initValue, cityCode, type) {
		// 清除列表
		county.options.length = 0;
		if (cityCode == "-1") {
			county.options[county.options.length] = new Option("-请选择(地区)-", "-1");
			county.style.display = "none";
			return;
		}
		if (cityCode == "0") {
			county.options[county.options.length] = new Option("----不限----", "0");
			county.style.display = "none";
			return;
		}
		cityCode = cityCode.substring(0, 9);
		initValue = initValue.substring(0, 12);
		var countyCodeList = eval("CountyCodes" + cityCode + ".split(',')");
		if (countyCodeList == "")
			county.style.display = "none";
		else
			county.style.display = "";
		var countyNameList = eval("CountyNames" + cityCode + ".split(',')");
		if (type == "-1") {
			county.options[county.options.length] = new Option("-请选择(地区)-", "-1");
		} else {
			county.options[county.options.length] = new Option("----不限----", "0");
		}
		for ( var i = 0; i < countyCodeList.length; i++) {
			if (countyNameList[i] != "") {
				var option = new Option(countyNameList[i], countyCodeList[i]);
				county.options[county.options.length] = option;
				if (countyCodeList[i] == initValue) {
					option.selected = true;
				}
			}
		}
	}
	
	// 装载城市
	this.setupCity = function(city, initValue, provinceCode, type) {
		// 清除列表
		city.options.length = 0;
		if (provinceCode == "-1") {
			city.options[city.options.length] = new Option("-请选择(城市)-", "-1");
			city.style.display = "none";
			return;
		}
		if (provinceCode == "0") {
			city.options[city.options.length] = new Option("----不限----", "0");
			city.style.display = "none";
			return;
		}
		provinceCode = provinceCode.substring(0, 6);
		initValue = initValue.substring(0, 9);
		var cityCodeList = eval("CityCodes" + provinceCode + ".split(',')");
		if (cityCodeList == "")
			city.style.display = "none";
		else
			city.style.display = "";
		var cityNameList = eval("CityNames" + provinceCode + ".split(',')");
		if (type == "-1") {
			city.options[city.options.length] = new Option("-请选择(城市)-", "-1");
		} else {
			city.options[city.options.length] = new Option("----不限----", "0");
		}
		for ( var i = 0; i < cityCodeList.length; i++) {
			if (cityNameList[i] != "") {
				var option = new Option(cityNameList[i], cityCodeList[i]);
				city.options[city.options.length] = option;
				if (cityCodeList[i] == initValue) {
					option.selected = true;
				}
			}
		}
	}
	// 装载省份
	this.setupProvince = function(province, initValue, countryCode, type) {
		// 清除列表

		province.options.length = 0;
		if (countryCode == "") {
			province.style.display = "none";
			return;
		}
		if (countryCode == "-1") {
			province.options[province.options.length] = new Option("-请选择(省份)-",
					"-1");
			return;
		}
		if (countryCode == "0") {
			province.options[province.options.length] = new Option(
					"----不限----", "0");
			return;
		}
		countryCode = countryCode.substring(0, 3);
		initValue = initValue.substring(0, 6);
		var provinceCodeList = eval("ProvinceCodes" + countryCode
				+ ".split(',')");
		if (provinceCodeList == "")
			province.style.display = "none";
		else
			province.style.display = "";
		var provinceNameList = eval("ProvinceNames" + countryCode
				+ ".split(',')");
		if (type == "-1") {
			province.options[province.options.length] = new Option("-请选择(省份)-",
					"-1");
		} else {
			province.options[province.options.length] = new Option(
					"----不限----", "0");
		}
		for ( var i = 0; i < provinceCodeList.length; i++) {
			if (provinceNameList[i] != "") {
				var option = new Option(provinceNameList[i],
						provinceCodeList[i]);
				province.options[province.options.length] = option;

				if (provinceCodeList[i] == initValue) {
					option.selected = true;
				}
			}
		}
	}

	// 装载国家
	this.setupCountry = function(Country, initValue) {
		initValue = initValue.substring(0, 3);
		var countryCodeList = countryCodes.split(',');
		var countryNameList = countryNames.split(',');
		for ( var i = 0; i < countryCodeList.length; i++) {
			var option = new Option(countryNameList[i], countryCodeList[i]);
			Country.options[Country.options.length] = option;

			if (countryCodeList[i] == initValue) {
				option.selected = true;
			}
		}
	}

	this.setupCountry_Query = function(Country, initValue, type) {
		var provinceCodeList = countryCodes.split(',');
		var provinceNameList = countryNames.split(',');
		if (type == "-1") {
			Country.options[Country.options.length] = new Option("-请选择(省份)-",
					"-1");
		} else {
			Country.options[Country.options.length] = new Option("----不限----",
					"0");
		}
		for ( var i = 0; i < provinceCodeList.length; i++) {
			var option = new Option(provinceNameList[i], provinceCodeList[i]);
			Country.options[Country.options.length] = option;

			if (provinceCodeList[i] == initValue) {
				option.selected = true;
			}
		}
	}

	/***************************************************************************
	 * /* /* 根据省份的编码取得省份的名称。 /* 如果省份的编码不存在返回空的字符串。 /* /
	 **************************************************************************/
	this.getProvinceNameByCode = function(name, code, size) {
		var provinceCodes = eval(name + "Codes" + size);
		var provinceNames = eval(name + "Names" + size);
		name = "";
		var provinceCodeList = provinceCodes.split(',');
		var provinceNameList = provinceNames.split(',');
		for ( var i = 0; i < provinceCodeList.length; i++) {
			if (code == provinceCodeList[i]) {
				name = provinceNameList[i];
				break;
			}
		}
		return name;
	}

	/**
	 * 根据编码得省份
	 * 
	 * @param Defultvalue
	 * @return
	 */
	this.xxxdefaultvalue = function(Defultvalue) {
		var vst;
		this.xxxdefaultvalue = '';
		var code = Defultvalue.replace(" ", "");
		if (code.length >= 6) {
			vst = code.substring(0, 6);
			this.xxxdefaultvalue += getProvinceNameByCode('Province', vst, vst.substring(0,
					3))
					+ "省";
		}
		if (code.length >= 9) {
			vst = code.substring(0, 9);
			this.xxxdefaultvalue += getProvinceNameByCode('City', vst, vst.substring(0, 6))
					+ "市";
		}
		document.write(this.xxxdefaultvalue);
	}

	/**
	 * 用于AJAX情况下获取区域名称
	 */
	this.xxxdefaultvalueInAJAX = function(Defultvalue, rtnId) {
		var vst;
		this.xxxdefaultvalue = '';
		var code = Defultvalue.replace(" ", "");
		if (code.length >= 6) {
			vst = code.substring(0, 6);
			this.xxxdefaultvalue += getProvinceNameByCode('Province', vst, vst.substring(0,
					3))
					+ "省";
		}
		if (code.length >= 9) {
			vst = code.substring(0, 9);
			this.xxxdefaultvalue += getProvinceNameByCode('City', vst, vst.substring(0, 6))
					+ "市";
		}
		$("#" + rtnId).html(this.xxxdefaultvalue);
	}

	this.getCountryNameByAreaCode = function(areaCode, rtnId) {
		var vst;
		var countryName = '';
		areaCode = areaCode.replace(" ", "");
		if (areaCode.length >= 3) {
			vst = areaCode.substring(0, 3);
			countryName += getCountryNameByCode('country', vst);
		}
		$("#" + rtnId).html(countryName);
	}

	this.getCountryNameByCode = function(name, code) {
		var provinceCodes = eval(name + "Codes");
		var provinceNames = eval(name + "Names");
		name = "";
		var provinceCodeList = provinceCodes.split(',');
		var provinceNameList = provinceNames.split(',');
		for ( var i = 0; i < provinceCodeList.length; i++) {
			if (code == provinceCodeList[i]) {
				name = provinceNameList[i];
				break;
			}
		}
		return name;
	}

	/**
	 * 获取国家名称
	 * 
	 * @param code
	 *            国家编码或者区域编码
	 */
	this.getCountryName = function(code) {
		var rtn = "";
		code = code.replace(" ", "");
		if (code.length > 3) {
			code = code.substring(0, 3);
		}
		var countryCodesList = eval("countryCodes").split(',');
		var countryNamesList = eval("countryNames").split(',');
		for ( var i = 0; i < countryCodesList.length; i++) {
			if (code == countryCodesList[i]) {
				rtn = countryNamesList[i];
				break;
			}
		}
		return rtn;
	}

	/**
	 * 获取省份名称
	 * 
	 * @param code
	 *            省份编码或者区域编码
	 */
	this.getProvinceName = function(code) {
		var rtn = "";
		code = code.replace(" ", "");
		if (code.length > 6) {
			code = code.substring(0, 6);
		}
		var countryCode = code.substring(0, 3);
		var provinceCodes = eval("ProvinceCodes" + countryCode);
		var provinceNames = eval("ProvinceNames" + countryCode);
		var provinceCodesList = provinceCodes.split(',');
		var provinceNamesList = provinceNames.split(',');
		for ( var i = 0; i < provinceCodesList.length; i++) {
			if (code == provinceCodesList[i]) {
				rtn = provinceNamesList[i];
				break;
			}
		}
		return rtn;
	}

	/**
	 * 获取城市名称
	 * 
	 * @param code
	 *            城市编码或者区域编码
	 */
	this.getCityName = function(code) {
		var rtn = "";
		code = code.replace(" ", "");
		if (code.length > 9) {
			code = code.substring(0, 9);
		}
		var provinceCode = code.substring(0, 6);
		var cityCodes = eval("CityCodes" + provinceCode);
		var cityNames = eval("CityNames" + provinceCode);
		var cityCodesList = cityCodes.split(',');
		var cityNamesList = cityNames.split(',');
		for ( var i = 0; i < cityCodesList.length; i++) {
			if (code == cityCodesList[i]) {
				rtn = cityNamesList[i];
				break;
			}
		}
		return rtn;
	}

	this.convert = function(areaCode, rtnId) {
		var vst;
		var rtn = '';
		areaCode = areaCode.replace(" ", "");
		switch (areaCode.length) {
		case 3:
			vst = areaCode;
			rtn += getCountryName(vst.substring(0, 3));
			break;
		case 6:
			vst = areaCode;
			rtn += getCountryName(vst.substring(0, 3));
			rtn += "　";
			rtn += getProvinceName(vst.substring(0, 6));
			break;
		case 9:
			vst = areaCode;
			rtn += getCountryName(vst.substring(0, 3));
			rtn += "　";
			rtn += getProvinceName(vst.substring(0, 6));
			rtn += "　";
			rtn += getCityName(vst.substring(0, 9));
			break;
		default:
			break;
		}
		$("#" + rtnId).html(rtn);
	}
}

//--------------------------------------------国家
var countryCodes="001,002,003,004,005,006,007,008,009,010,011,012,013,014,016,018,019,020,021,022,023,024,028";
var countryNames="中国,美国,英国,日本,意大利,法国,阿富汗,阿尔巴尼亚,瑞士,丹麦,德国,西班牙,芬兰,希腊,韩国,马尔代夫,荷兰,葡萄牙,俄罗斯,新加坡,菲律宾,瑞典,马来西亚";
//--------------------------------------------省份
var ProvinceCodes001="001001,001002,001003,001004,001005,001006,001007,001008,001009,001010,001011,001012,001013,001014,001015,001016,001017,001018,001019,001020,001021,001022,001023,001024,001026,001027,001028,001029,001030,001031,001032,001033,001034,001035";
var ProvinceNames001="浙江,江苏,山东,山西,河南,河北,湖南,湖北,广东,广西,黑龙江,辽宁,安徽,福建,甘肃,江西,云南,贵州,四川,青海,陕西,吉林,宁夏,海南,西藏,内蒙古,新疆,天津,北京,上海,重庆,台湾,香港,澳门";
var ProvinceCodes002="";
var ProvinceNames002="";
var ProvinceCodes003="";
var ProvinceNames003="";
var ProvinceCodes004="";
var ProvinceNames004="";
var ProvinceCodes005="";
var ProvinceNames005="";
var ProvinceCodes006="";
var ProvinceNames006="";
var ProvinceCodes007="";
var ProvinceNames007="";
var ProvinceCodes008="";
var ProvinceNames008="";
var ProvinceCodes009="";
var ProvinceNames009="";
var ProvinceCodes010="";
var ProvinceNames010="";
var ProvinceCodes011="";
var ProvinceNames011="";
var ProvinceCodes012="";
var ProvinceNames012="";
var ProvinceCodes013="";
var ProvinceNames013="";
var ProvinceCodes014="";
var ProvinceNames014="";
var ProvinceCodes016="";
var ProvinceNames016="";
var ProvinceCodes018="";
var ProvinceNames018="";
var ProvinceCodes019="";
var ProvinceNames019="";
var ProvinceCodes020="";
var ProvinceNames020="";
var ProvinceCodes021="";
var ProvinceNames021="";
var ProvinceCodes022="";
var ProvinceNames022="";
var ProvinceCodes023="";
var ProvinceNames023="";
var ProvinceCodes024="";
var ProvinceNames024="";
var ProvinceCodes025="";
var ProvinceNames025="";
var ProvinceCodes026="";
var ProvinceNames026="";
var ProvinceCodes027="";
var ProvinceNames027="";
var ProvinceCodes028="";
var ProvinceNames028="";

//---------------------------------------------城市

//浙江
var CityCodes001001="001001001,001001002,001001003,001001004,001001005,001001006,001001007,001001008,001001009,001001010,001001011";
var CityNames001001="杭州,宁波,温州,嘉兴,湖州,绍兴,金华,衢州,台州,舟山,丽水";

//江苏
var CityCodes001002="001002001,001002002,001002003,001002004,001002005,001002006,001002007,001002008,001002009,001002010,001002011,001002012,001002013";
var CityNames001002="苏州,淮安,无锡,南京,常州,扬州,连云港,南通,宿迁,泰州,徐州,盐城,镇江";

//山东
var CityCodes001003="001003001,001003002,001003003,001003004,001003005,001003006,001003007,001003008,001003009,001003010,001003011,001003012,001003013,001003014,001003015,001003016,001003017";
var CityNames001003="济宁,青岛,泰安,德州,临沂,烟台,威海,济南,东营,荷泽,聊城,莱芜,日照,潍坊,枣庄,淄博,滨州";

//山西
var CityCodes001004="001004001,001004002,001004003,001004004,001004005,001004006,001004007,001004008,001004009,001004010,001004011";
var CityNames001004="太原,大同,忻州,长治,晋城,晋中,临汾,阳泉,朔州,吕梁,运城";

//河南
var CityCodes001005="001005001,001005002,001005003,001005004,001005005,001005006,001005007,001005008,001005009,001005010,001005011,001005012,001005013,001005014,001005015,001005016,001005017,001005018,001005019";
var CityNames001005="开封,驻马店,许昌,郑州,周口,濮阳,平顶山,洛阳,济源,漯河,鹤壁,南阳,三门峡,安阳,信阳,商丘,焦作,新乡,济源市";

//河北
var CityCodes001006="001006001,001006002,001006003,001006004,001006005,001006006,001006007,001006008,001006009,001006010,001006011";
var CityNames001006="秦皇岛,邯郸,唐山,承德,张家口,保定,石家庄,沧州,衡水,邢台,廊坊";

//湖南
var CityCodes001007="001007001,001007002,001007003,001007004,001007005,001007006,001007007,001007008,001007009,001007010,001007011,001007012,001007013,001007014";
var CityNames001007="长沙,常德,衡阳,岳阳,张家界,湘潭,郴州,怀化,娄底,邵阳,益阳,永州,株洲,湘西土家族苗族自治州";

//湖北
var CityCodes001008="001008001,001008002,001008003,001008004,001008005,001008006,001008007,001008008,001008009,001008010,001008011,001008012,001008013";
var CityNames001008="宜昌,武汉,黄冈,十堰,恩施土家族苗族自治州,鄂州,黄石,荆州,荆门,襄樊,咸宁,孝感,随州";

//广东
var CityCodes001009="001009001,001009002,001009003,001009004,001009005,001009006,001009007,001009008,001009009,001009010,001009011,001009012,001009013,001009014,001009015,001009016,001009017,001009018,001009019,001009020,001009021";
var CityNames001009="深圳,佛山,河源,江门,阳江,汕头,梅州,云浮,韶关,汕尾,肇庆,中山,珠海,潮州,清远,广州,东莞,惠州,揭阳,茂名,湛江";

//广西
var CityCodes001010="001010001,001010002,001010003,001010004,001010005,001010006,001010007,001010008,001010009,001010010,001010011,001010012,001010013";
var CityNames001010="桂林,南宁,北海,柳州,贺州,百色,河池,梧州,玉林,防城港,贵港,崇左,来宾";

//黑龙江
var CityCodes001011="001011001,001011002,001011003,001011004,001011005,001011006,001011007,001011008,001011009,001011010,001011011,001011012,001011013";
var CityNames001011="哈尔滨,黑河,大庆,鹤岗,佳木斯,鸡西,牡丹江,齐齐哈尔,双鸭山,伊春,七台河,绥化,大兴安岭地区";

//辽宁
var CityCodes001012="001012001,001012002,001012003,001012004,001012005,001012006,001012007,001012008,001012009,001012010,001012011,001012012,001012013,001012014";
var CityNames001012="丹东,沈阳,大连,鞍山,本溪,朝阳,抚顺,锦州,铁岭,营口,葫芦岛,盘锦,阜新,辽阳";

//安徽
var CityCodes001013="001013001,001013002,001013003,001013004,001013005,001013006,001013007,001013008,001013009,001013010,001013011,001013012,001013013,001013014,001013015,001013016,001013017";
var CityNames001013="合肥,宣城,黄山,蚌埠,安庆,巢湖,滁州,阜阳,淮北,淮南,马鞍山,宿州,铜陵,芜湖,池州,六安,亳州";

//福建
var CityCodes001014="001014001,001014002,001014003,001014004,001014005,001014006,001014007,001014008,001014009";
var CityNames001014="福州,厦门,南平,泉州,龙岩,莆田,三明,漳州,宁德";

//甘肃
var CityCodes001015="001015001,001015002,001015003,001015004,001015005,001015006,001015007,001015008,001015009,001015010,001015011,001015012,001015013,001015014";
var CityNames001015="兰州,酒泉,天水,嘉峪关,金昌,临夏回族自治州,平凉,武威,白银,定西,张掖,庆阳,陇南,甘南藏族自治州";

//江西
var CityCodes001016="001016001,001016002,001016003,001016004,001016005,001016006,001016007,001016008,001016009,001016010,001016011";
var CityNames001016="南昌,景德镇,九江,赣州,吉安,抚州,萍乡,新余,上饶,宜春,鹰潭";

//云南
var CityCodes001017="001017001,001017002,001017003,001017004,001017005,001017006,001017007,001017008,001017009,001017010,001017011,001017012,001017013,001017014,001017015,001017016";
var CityNames001017="昆明,丽江,大理白族自治州,曲靖,玉溪,昭通,思茅,临沧,保山,文山壮族苗族自治州,红河哈尼族彝族自治州,西双版纳傣族自治州,楚雄彝族自治州,德宏傣族景颇族自治州,怒江傈僳族自治州,迪庆藏族自治州";

//贵州
var CityCodes001018="001018001,001018002,001018003,001018004,001018005,001018006,001018007,001018008,001018009";
var CityNames001018="贵阳,安顺,毕节地区,铜仁地区,黔西南布依族苗族自治州,六盘水,遵义,黔东南苗族侗族自治州,黔南布依族苗族自治州";

//四川
var CityCodes001019="001019001,001019002,001019003,001019004,001019005,001019006,001019007,001019008,001019009,001019010,001019011,001019012,001019013,001019014,001019015,001019016,001019017,001019018,001019019,001019020,001019021";
var CityNames001019="成都,德阳,广元,乐山,泸州,绵阳,南充,内江,攀枝花,遂宁,宜宾,自贡,广安,雅安,达州,巴中,眉山,资阳,阿坝藏族羌族自治州,甘孜藏族自治州,凉山彝族自治州";

//青海
var CityCodes001020="001020001,001020002,001020003,001020004,001020005,001020006,001020007,001020008";
var CityNames001020="西宁,海东地区,海北藏族自治州,黄南藏族自治州,海南藏族自治州,果洛藏族自治州,玉树藏族自治州,海西蒙古族藏族自治州";

//陕西
var CityCodes001021="001021001,001021002,001021003,001021004,001021005,001021006,001021007,001021008,001021009,001021010";
var CityNames001021="西安,延安,咸阳,宝鸡,汉中,铜川,渭南,榆林,商洛,安康";

//吉林
var CityCodes001022="001022001,001022002,001022003,001022004,001022005,001022006,001022007,001022008,001022009";
var CityNames001022="白城,白山,长春,辽源,四平,通化,松原,延边朝鲜族自治州,吉林市";

//宁夏
var CityCodes001023="001023001,001023002,001023003,001023004,001023005";
var CityNames001023="固原,石嘴山,吴忠,中卫,银川";

//海南
var CityCodes001024="001024001,001024002";
var CityNames001024="三亚,海口";

//西藏
var CityCodes001026="001026001,001026002,001026003,001026004,001026005,001026006,001026007";
var CityNames001026="拉萨,那曲地区,昌都地区,山南地区,日喀则地区,阿里地区,林芝地区";

//内蒙古
var CityCodes001027="001027001,001027002,001027003,001027004,001027005,001027006,001027007,001027008,001027009,001027010,001027011,001027012";
var CityNames001027="呼伦贝尔,通辽,包头,赤峰,乌海,乌兰察布,呼和浩特,锡林郭勒盟,鄂尔多斯,巴彦淖尔,阿拉善盟,兴安盟";

//新疆
var CityCodes001028="001028001,001028002,001028003,001028004,001028005,001028006,001028007,001028008,001028009,001028010,001028011,001028012,001028013,001028014,001028015,001028016,001028017,001028018";
var CityNames001028="乌鲁木齐,阿克苏地区,哈密地区,和田地区,克拉玛依,喀什地区,吐鲁番地区,克孜勒苏柯尔克孜自治州,巴音郭楞蒙古自治州,昌吉回族自治州,博尔塔拉蒙古自治州,伊犁哈萨克自治州,塔城地区,阿勒泰地区,石河子,阿拉尔,图木斯克,五家渠";

//天津
var CityCodes001029="001029001";
var CityNames001029="天津市";

//北京
var CityCodes001030="001030001";
var CityNames001030="北京市";

//上海
var CityCodes001031="001031001";
var CityNames001031="上海市";

//重庆
var CityCodes001032="001032001";
var CityNames001032="重庆市";
//台湾
var CityCodes001033="001033001";
var CityNames001033="台湾";
//香港
var CityCodes001034="001034001";
var CityNames001034="香港";
//澳门
var CityCodes001035="001035001";
var CityNames001035="澳门";
var CountyNames001003012="莱城区,钢城区";
var CountyCodes001003012="001003012001,001003012002";
var CountyNames001003013="东港区,岚山区,五莲县,莒县";
var CountyCodes001003013="001003013003,001003013004,001003013001,001003013002";
var CountyNames001003014="潍城区,寒亭区,坊子区,奎文区,青州市,褚城市,寿光市,安丘市,高密市,昌邑市,昌乐县,临朐县";
var CountyCodes001003014="001003014009,001003014010,001003014011,001003014012,001003014001,001003014002,001003014003,001003014004,001003014005,001003014006,001003014007,001003014008";
var CountyNames001003015="薛城区,峄城区,台儿庄区,山亭区,滕州市";
var CountyCodes001003015="001003015002,001003015003,001003015004,001003015005,001003015001";
var CountyNames001003016="淄川区,张店区,博山区,临淄区,周村区,桓台县,高青县,沂源县";
var CountyCodes001003016="001003016004,001003016005,001003016006,001003016007,001003016008,001003016001,001003016002,001003016003";
var CountyNames001003017="滨城区,邹平县,沾化县,惠民县,博兴县,阳信县,无棣县";
var CountyCodes001003017="001003017001,001003017002,001003017003,001003017004,001003017005,001003017006,001003017007";
var CountyNames001004004="长治县,襄垣县,屯留县,平顺县,黎城县,壶关县,长子县,武乡县,沁县,沁源县,潞城市";
var CountyCodes001004004="001004004001,001004004002,001004004003,001004004004,001004004005,001004004006,001004004007,001004004008,001004004009,001004004010,001004004011";
var CountyNames001004005="沁水县,阳城县,陵川县,泽州县,高平市";
var CountyCodes001004005="001004005001,001004005002,001004005003,001004005004,001004005005";
var CountyNames001004006="榆次区,榆社县,左权县,和顺县,昔阳县,寿阳县,太谷县,祁县,平遥县,灵石县,介休市";
var CountyCodes001004006="001004006001,001004006002,001004006003,001004006004,001004006005,001004006006,001004006007,001004006008,001004006009,001004006010,001004006011";
var CountyNames001004007="尧都区,曲沃县,翼城县,襄汾县,洪洞县,古县,安泽县,浮山县,吉县,乡宁县,大宁县,隰县,永和县,蒲县,汾西县,侯马市,霍州市";
var CountyCodes001004007="001004007001,001004007002,001004007003,001004007004,001004007005,001004007006,001004007007,001004007008,001004007009,001004007010,001004007011,001004007012,001004007013,001004007014,001004007015,001004007016,001004007017";
var CountyNames001004008="矿区,平定县,盂县";
var CountyCodes001004008="001004008001,001004008002,001004008003";
var CountyNames001004009="朔城区,平鲁区,山阴县,应县,右玉县,怀仁县";
var CountyCodes001004009="001004009001,001004009002,001004009003,001004009004,001004009005,001004009006";
var CountyNames001004010="离石区,文水县,交城县,兴县,临县,柳林县,石楼县,岚县,方山县,中阳县,交口县,孝义市,汾阳市";
var CountyCodes001004010="001004010001,001004010002,001004010003,001004010004,001004010005,001004010006,001004010007,001004010008,001004010009,001004010010,001004010011,001004010012,001004010013";
var CountyNames001004011="盐湖区,临猗县,万荣县,闻喜县,稷山县,新绛县,绛县,垣曲县,夏县,平陆县,芮城县,永济市,河津市";
var CountyCodes001004011="001004011001,001004011002,001004011003,001004011004,001004011005,001004011006,001004011007,001004011008,001004011009,001004011010,001004011011,001004011012,001004011013";
var CountyNames001005017="解放区,中站区,马村区,山阳区,修武县,博爱县,武陟县,温　县,沁阳市,孟州市";
var CountyCodes001005017="001005017001,001005017002,001005017003,001005017004,001005017005,001005017006,001005017007,001005017008,001005017009,001005017010";
var CountyNames001005018="红旗区,卫滨区,凤泉区,牧野区,新乡县,获嘉县,原阳县,延津县,封丘县,长垣县,卫辉市,辉县市";
var CountyCodes001005018="001005018001,001005018002,001005018003,001005018004,001005018005,001005018006,001005018007,001005018008,001005018009,001005018010,001005018011,001005018012";
var CountyNames001005019=""
var CountyCodes001005019=""
var CountyNames001006008="新华 区,运河区,沧　县,青　县,东光县,海兴县,盐山县,肃宁县,南皮县,吴桥县,献　县,孟村回族自治县,泊头市,任丘市,黄骅市,河间市";
var CountyCodes001006008="001006008001,001006008002,001006008003,001006008004,001006008005,001006008006,001006008007,001006008008,001006008009,001006008010,001006008011,001006008012,001006008013,001006008014,001006008015,001006008016";
var CountyNames001006009="安平县,故城县,景　县,阜城县,冀州市,深州市,桃城区,枣强县,武邑县,武强县,饶阳县";
var CountyCodes001006009="001006009006,001006009007,001006009008,001006009009,001006009010,001006009011,001006009001,001006009002,001006009003,001006009004,001006009005";
var CountyNames001006010="桥东区,桥西区,邢台县,临城县,内丘县,柏乡县,隆尧县,任　县,南和县,宁晋县,巨鹿县,新河县,广宗县,平乡县,威　县,清河县,临西县,南宫市,沙河市";
var CountyCodes001006010="001006010001,001006010002,001006010003,001006010004,001006010005,001006010006,001006010007,001006010008,001006010009,001006010010,001006010011,001006010012,001006010013,001006010014,001006010015,001006010016,001006010017,001006010018,001006010019";
var CountyNames001006011="安次区,广阳区,固安县,永清县,香河县,大城县,文安县,大厂回族自治县,霸州市,三河市";
var CountyCodes001006011="001006011001,001006011002,001006011003,001006011004,001006011005,001006011006,001006011007,001006011008,001006011009,001006011010";
var CountyNames001007007="北湖区,苏仙区,桂阳县,宜章县,永兴县,嘉禾县,临武县,汝城县,桂东县,安仁县,资兴市";
var CountyCodes001007007="001007007001,001007007002,001007007003,001007007004,001007007005,001007007006,001007007007,001007007008,001007007009,001007007010,001007007011";
var CountyNames001007008="鹤城区,中方县,沅陵县,辰溪县,溆浦县,会同县,麻阳苗族自治县,新晃侗族自治县,芷江侗族自治县,靖州苗族侗族自治县,通道侗族自治县,洪江市";
var CountyCodes001007008="001007008001,001007008002,001007008003,001007008004,001007008005,001007008006,001007008007,001007008008,001007008009,001007008010,001007008011,001007008012";
var CountyNames001007009="娄星区,双峰县,新化县,冷水江市,涟源市";
var CountyCodes001007009="001007009001,001007009002,001007009003,001007009004,001007009005";
var CountyNames001007010="双清区,大祥区,北塔区,邵东县,新邵县,邵阳县,隆回县,洞口县,绥宁县,新宁县,城步苗族自治县,武冈市";
var CountyCodes001007010="001007010001,001007010002,001007010003,001007010004,001007010005,001007010006,001007010007,001007010008,001007010009,001007010010,001007010011,001007010012";
var CountyNames001007011="资阳区,赫山区,南　县,桃江县,安化县,沅江市";
var CountyCodes001007011="001007011001,001007011002,001007011003,001007011004,001007011005,001007011006";
var CountyNames001007012="芝山区,冷水滩区,祁阳县,东安县,双牌县,道　县,江永县,宁远县,蓝山县,新田县,江华瑶族自治县";
var CountyCodes001007012="001007012001,001007012002,001007012003,001007012004,001007012005,001007012006,001007012007,001007012008,001007012009,001007012010,001007012011";
var CountyNames001007013="荷塘区,芦淞区,石峰区,天元区,株洲县,攸　县,茶陵县,炎陵县,醴陵市";
var CountyCodes001007013="001007013001,001007013002,001007013003,001007013004,001007013005,001007013006,001007013007,001007013008,001007013009";
var CountyNames001007014="吉首市,泸溪县,凤凰县,花垣县,保靖县,古丈县,永顺县,龙山县";
var CountyCodes001007014="001007014001,001007014002,001007014003,001007014004,001007014005,001007014006,001007014007,001007014008";
var CountyNames001008005="恩施市,利川市,建始县,巴东县,宣恩县,咸丰县,来凤县,鹤峰县,省直辖行政单位,仙桃市,潜江市,天门市,神农架林区";
var CountyCodes001008005="001008005001,001008005002,001008005003,001008005004,001008005005,001008005006,001008005007,001008005008,001008005009,001008005010,001008005011,001008005012,001008005013";
var CountyNames001008006="梁子湖区,华容区,鄂城区";
var CountyCodes001008006="001008006001,001008006002,001008006003";
var CountyNames001008007="黄石港区,西塞山区,下陆区,铁山区,阳新县,大冶市";
var CountyCodes001008007="001008007001,001008007002,001008007003,001008007004,001008007005,001008007006";
var CountyNames001008008="沙市区,荆州区,公安县,监利县,江陵县,石首市,洪湖市,松滋市";
var CountyCodes001008008="001008008001,001008008002,001008008003,001008008004,001008008005,001008008006,001008008007,001008008008";
var CountyNames001008009="东宝区,掇刀区,京山县,沙洋县,钟祥市";
var CountyCodes001008009="001008009001,001008009002,001008009003,001008009004,001008009005";
var CountyNames001008010="襄城区,樊城区,襄阳区,南漳县,谷城县,保康县,老河口市,枣阳市,宜城市";
var CountyCodes001008010="001008010001,001008010002,001008010003,001008010004,001008010005,001008010006,001008010007,001008010008,001008010009";
var CountyNames001008011="咸安区,嘉鱼县,通城县,崇阳县,通山县,赤壁市";
var CountyCodes001008011="001008011001,001008011002,001008011003,001008011004,001008011005,001008011006";
var CountyNames001026005="日喀则市,南木林县,江孜县,定日县,萨迦县,拉孜县,昂仁县,谢通门县,白朗县,仁布县,康马县,定结县,仲巴县,亚东县,吉隆县,聂拉木县,萨嘎县,岗巴县";
var CountyCodes001026005="001026005001,001026005002,001026005003,001026005004,001026005005,001026005006,001026005007,001026005008,001026005009,001026005010,001026005011,001026005012,001026005013,001026005014,001026005015,001026005016,001026005017,001026005018";
var CountyNames001026006="普兰县,札达县,噶尔县,日土县,革吉县,改则县,措勤县";
var CountyCodes001026006="001026006001,001026006002,001026006003,001026006004,001026006005,001026006006,001026006007";
var CountyNames001026007="林芝县,工布江达县,米林县,墨脱县,波密县,察隅县,朗　县";
var CountyCodes001026007="001026007001,001026007002,001026007003,001026007004,001026007005,001026007006,001026007007";
var CountyNames001027004="红山区,元宝山区,松山区,阿鲁科尔沁旗,巴林左旗,巴林右旗,林西县,克什克腾旗,翁牛特旗,喀喇沁旗,宁城县,敖汉旗";
var CountyCodes001027004="001027004001,001027004002,001027004003,001027004004,001027004005,001027004006,001027004007,001027004008,001027004009,001027004010,001027004011,001027004012";
var CountyNames001027005="乌达区,海勃湾区,海南区";
var CountyCodes001027005="001027005003,001027005001,001027005002";
var CountyNames001027006="察哈尔右翼后旗,四子王旗,丰镇市,集宁区,卓资县,化德县,商都县,兴和县,凉城县,察哈尔右翼前旗,察哈尔右翼中旗";
var CountyCodes001027006="001027006009,001027006010,001027006011,001027006001,001027006002,001027006003,001027006004,001027006005,001027006006,001027006007,001027006008";
var CountyNames001027007="新城区,回民区,玉泉区,赛罕区,土默特左旗,托克托县,和林格尔县,清水河县,武川县";
var CountyCodes001027007="001027007001,001027007002,001027007003,001027007004,001027007005,001027007006,001027007007,001027007008,001027007009";
var CountyNames001027008="二连浩特市,锡林浩特市,阿巴嘎旗,苏尼特左旗,苏尼特右旗,东乌珠穆沁旗,西乌珠穆沁旗,太仆寺旗,镶黄旗,正镶白旗,正蓝旗,多伦县";
var CountyCodes001027008="001027008001,001027008002,001027008003,001027008004,001027008005,001027008006,001027008007,001027008008,001027008009,001027008010,001027008011,001027008012";
var CountyNames001027009="东胜区,达拉特旗,准格尔旗,鄂托克前旗,鄂托克旗,杭锦旗,乌审旗,伊金霍洛旗";
var CountyCodes001027009="001027009001,001027009002,001027009003,001027009004,001027009005,001027009006,001027009007,001027009008";
var CountyNames001027010="临河区,五原县,磴口县,乌拉特前旗,乌拉特中旗,乌拉特后旗,杭锦后旗";
var CountyCodes001027010="001027010001,001027010002,001027010003,001027010004,001027010005,001027010006,001027010007";
var CountyNames001027011="阿拉善左旗,阿拉善右旗,额济纳旗";
var CountyCodes001027011="001027011001,001027011002,001027011003";
var CountyNames001027012="乌兰浩特市,阿尔山市,科尔沁右翼前旗,科尔沁右翼中旗,扎赉特旗,突泉县";
var CountyCodes001027012="001027012001,001027012002,001027012003,001027012004,001027012005,001027012006";
var CountyNames001028002="阿克苏市,温宿县,库车县,沙雅县,新和县,拜城县,乌什县,阿瓦提县,柯坪县";
var CountyCodes001028002="001028002001,001028002002,001028002003,001028002004,001028002005,001028002006,001028002007,001028002008,001028002009";
var CountyNames001028003="哈密市,巴里坤哈萨克自治县,伊吾县";
var CountyCodes001028003="001028003001,001028003002,001028003003";
var CountyNames001028004="和田市,和田县,墨玉县,皮山县,洛浦县,策勒县,于田县,民丰县";
var CountyCodes001028004="001028004001,001028004002,001028004003,001028004004,001028004005,001028004006,001028004007,001028004008";
var CountyNames001028005="独山子区,克拉玛依区,白碱滩区,乌尔禾区";
var CountyCodes001028005="001028005001,001028005002,001028005003,001028005004";
var CountyNames001028006="喀什市,疏附县,疏勒县,英吉沙县,泽普县,莎车县,叶城县,麦盖提县,岳普湖县,伽师县,巴楚县,塔什库尔干塔吉克自治县";
var CountyCodes001028006="001028006001,001028006002,001028006003,001028006004,001028006005,001028006006,001028006007,001028006008,001028006009,001028006010,001028006011,001028006012";
var CountyNames001028007="吐鲁番市,鄯善县,托克逊县";
var CountyCodes001028007="001028007001,001028007002,001028007003";
var CountyNames001028008="阿图什市,阿克陶县,阿合奇县,乌恰县";
var CountyCodes001028008="001028008001,001028008002,001028008003,001028008004";
var CountyNames001028009="库尔勒市,轮台县,尉犁县,若羌县,且末县,焉耆回族自治县,和静县,和硕县,博湖县";
var CountyCodes001028009="001028009001,001028009002,001028009003,001028009004,001028009005,001028009006,001028009007,001028009008,001028009009";
var CountyNames001028010="昌吉市,阜康市,米泉市,呼图壁县,玛纳斯县,奇台县,吉木萨尔县,木垒哈萨克自治县";
var CountyCodes001028010="001028010001,001028010002,001028010003,001028010004,001028010005,001028010006,001028010007,001028010008";
var CountyNames001028011="精河县,温泉县,博乐市";
var CountyCodes001028011="001028011002,001028011003,001028011001";
var CountyNames001028012="伊宁市,奎屯市,伊宁县,察布查尔锡伯自治县,霍城县,巩留县,新源县,昭苏县,特克斯县,尼勒克县";
var CountyCodes001028012="001028012001,001028012002,001028012003,001028012004,001028012005,001028012006,001028012007,001028012008,001028012009,001028012010";
var CountyNames001028013="塔城市,乌苏市,额敏县,沙湾县,托里县,裕民县,和布克赛尔蒙古自治县";
var CountyCodes001028013="001028013001,001028013002,001028013003,001028013004,001028013005,001028013006,001028013007";
var CountyNames001028014="阿勒泰市,布尔津县,富蕴县,福海县,哈巴河县,青河县,吉木乃县";
var CountyCodes001028014="001028014001,001028014002,001028014003,001028014004,001028014005,001028014006,001028014007";
var CountyNames001028015=""
var CountyCodes001028015=""
var CountyNames001028016=""
var CountyCodes001028016=""
var CountyNames001028017=""
var CountyCodes001028017=""
var CountyNames001028018=""
var CountyCodes001028018=""
var CountyNames001029001="和平 区,河东 区,河西区,南开区,河北区,红桥区,塘沽区,汉沽区,大港区,东丽区,西青区,津南区,北辰区,武清区,宝坻区,宁河县,静海县,蓟　县";
var CountyCodes001029001="001029001001,001029001002,001029001003,001029001004,001029001005,001029001006,001029001007,001029001008,001029001009,001029001010,001029001011,001029001012,001029001013,001029001014,001029001015,001029001016,001029001017,001029001018";
var CountyNames001030001="东城区,西城区,崇文区,宣武区,朝阳区,丰台区,石景山区,海淀区,门头沟区,房山区,通州区,顺义区,昌平区,大兴区,怀柔区,平谷区,密云县,延庆县";
var CountyCodes001030001="001030001001,001030001002,001030001003,001030001004,001030001005,001030001006,001030001007,001030001008,001030001009,001030001010,001030001011,001030001012,001030001013,001030001014,001030001015,001030001016,001030001017,001030001018";
var CountyNames001031001="卢湾区,徐汇区,长宁区,静安区,闸北区,虹口区,杨浦区,闵行区,宝山区,嘉定区,浦东新区,金山区,松江区,青浦区,南汇区,奉贤区,崇明县,黄浦区";
var CountyCodes001031001="001031001002,001031001003,001031001004,001031001005,001031001006,001031001007,001031001008,001031001009,001031001010,001031001011,001031001012,001031001013,001031001014,001031001015,001031001016,001031001017,001031001018,001031001001";
var CountyNames001032001="万州区,涪陵区,渝中区,大渡口区,沙坪坝区,九龙坡区,南岸区,北碚区,万盛区,双桥区,渝北区,巴南区,黔江区,长寿区,綦江县,潼南县,铜梁县,大足县,荣昌县,璧山县,梁平县,城口县,丰都县,垫江县,武隆县,忠　县,开　县,云阳县,奉节县,巫山县,巫溪县,石柱土家族自治县,秀山土家族苗族自治县,酉阳土家族苗族自治县,彭水苗族土家族自治县,江津市,合川市,永川市,南川市";
var CountyCodes001032001="001032001001,001032001002,001032001003,001032001004,001032001005,001032001006,001032001007,001032001008,001032001009,001032001010,001032001011,001032001012,001032001013,001032001014,001032001015,001032001016,001032001017,001032001018,001032001019,001032001020,001032001021,001032001022,001032001023,001032001024,001032001025,001032001026,001032001027,001032001028,001032001029,001032001030,001032001031,001032001032,001032001033,001032001034,001032001035,001032001036,001032001037,001032001038,001032001039";
var CountyNames001001001="上城区,下城区,江干区,拱墅区,西湖区,滨江区,萧山区,建德市,富阳市,余杭区,临安市,桐庐县,淳安县";
var CountyCodes001001001="001001001008,001001001009,001001001010,001001001011,001001001012,001001001013,001001001001,001001001002,001001001003,001001001004,001001001005,001001001006,001001001007";
var CountyNames001001002="海曙区,江东区,江北区,北仑区,镇海区,余姚市,慈溪市,奉化市,鄞州区,宁海县,象山县";
var CountyCodes001001002="001001002007,001001002008,001001002009,001001002010,001001002011,001001002001,001001002002,001001002003,001001002004,001001002005,001001002006";
var CountyNames001001003="鹿城区,龙湾区,瓯海区,瑞安市,乐清市,永嘉县,洞头县,平阳县,苍南县,文成县,泰顺县";
var CountyCodes001001003="001001003009,001001003010,001001003011,001001003001,001001003002,001001003003,001001003004,001001003005,001001003006,001001003007,001001003008";
var CountyNames001001004="南湖区,秀洲区,海宁市,平湖市,桐乡市,嘉善县,海盐县";
var CountyCodes001001004="001001004006,001001004007,001001004001,001001004002,001001004003,001001004004,001001004005";
var CountyNames001002005="天宁区,钟楼区,戚墅堰区,新北区,金坛市,溧阳市,武进区";
var CountyCodes001002005="001002005004,001002005005,001002005006,001002005007,001002005001,001002005002,001002005003";
var CountyNames001002006="广陵区,维扬区,邗江区,高邮市,江都市,仪征市,宝应县";
var CountyCodes001002006="001002006006,001002006007,001002006001,001002006002,001002006003,001002006004,001002006005";
var CountyNames001002007="连云区,新浦区,海州区,东海县,灌云县,赣榆县,灌南县";
var CountyCodes001002007="001002007005,001002007006,001002007007,001002007001,001002007002,001002007003,001002007004";
var CountyNames001002008="崇川区,港闸区,如皋市,通州市,海门市,启东市,海安县,如东县";
var CountyCodes001002008="001002008007,001002008008,001002008001,001002008002,001002008003,001002008004,001002008005,001002008006";
var CountyNames001002009="宿城区,宿豫区,沭阳县,泗阳县,泗洪县";
var CountyCodes001002009="001002009005,001002009001,001002009002,001002009003,001002009004";
var CountyNames001002010="海陵区,高港区,泰兴市,姜堰市,靖江市,兴化市";
var CountyCodes001002010="001002010005,001002010006,001002010001,001002010002,001002010003,001002010004";
var CountyNames001002011="云龙区,九里区,贾汪区,泉山区,邳州市,新沂市,铜山县,睢宁县,沛县,丰县";
var CountyCodes001002011="001002011007,001002011008,001002011009,001002011010,001002011001,001002011002,001002011003,001002011004,001002011005,001002011006";
var CountyNames001002012="亭湖区,东台市,大丰市,盐都区,建湖县,响水县,阜宁县,射阳县,滨海县";
var CountyCodes001002012="001002012009,001002012001,001002012002,001002012003,001002012004,001002012005,001002012006,001002012007,001002012008";
var CountyNames001002013="京口区,润州区,丹阳市,扬中市,句容市,丹徒区";
var CountyCodes001002013="001002013005,001002013006,001002013001,001002013002,001002013003,001002013004";
var CountyNames001003009="东营区,河口区,垦利县,广饶县,利津县";
var CountyCodes001003009="001003009004,001003009005,001003009001,001003009002,001003009003";
var CountyNames001003010="牡丹区,鄄城县,单县,郓城县,曹县,定陶县,巨野县,东明县,成武县";
var CountyCodes001003010="001003010001,001003010002,001003010003,001003010004,001003010005,001003010006,001003010007,001003010008,001003010009";
var CountyNames001003011="东昌府区,阳谷县,茌平县,莘县,东阿县,冠县,临清市,高唐县";
var CountyCodes001003011="001003011008,001003011003,001003011004,001003011005,001003011006,001003011007,001003011001,001003011002";
var CountyNames001010003="海城区,银海区,铁山港区,合浦县";
var CountyCodes001010003="001010003001,001010003002,001010003003,001010003004";
var CountyNames001010004="城中区,鱼峰区,柳南区,柳北区,柳江县,柳城县,鹿寨县,融安县,融水苗族自治县,三江侗族自治县";
var CountyCodes001010004="001010004001,001010004002,001010004003,001010004004,001010004005,001010004006,001010004007,001010004008,001010004009,001010004010";
var CountyNames001010005="八步区,昭平县,钟山县,富川瑶族自治县";
var CountyCodes001010005="001010005001,001010005002,001010005003,001010005004";
var CountyNames001011001="道里区,南岗区,道外区,香坊区,动力区,平房区,松北区,呼兰区,依兰县,方正县,宾　县,巴彦县,木兰县,通河县,延寿县,阿城市,双城市,尚志市,五常市";
var CountyCodes001011001="001011001001,001011001002,001011001003,001011001004,001011001005,001011001006,001011001007,001011001008,001011001009,001011001010,001011001011,001011001012,001011001013,001011001014,001011001015,001011001016,001011001017,001011001018,001011001019";
var CountyNames001011002="爱辉区,嫩江县,逊克县,孙吴县,北安市,五大连池市";
var CountyCodes001011002="001011002001,001011002002,001011002003,001011002004,001011002005,001011002006";
var CountyNames001011003="萨尔图区,龙凤区,让胡路区,红岗区,大同区,肇州县,肇源县,林甸县,杜尔伯特蒙古族自治县";
var CountyCodes001011003="001011003001,001011003002,001011003003,001011003004,001011003005,001011003006,001011003007,001011003008,001011003009";
var CountyNames001012001="元宝区,振兴区,振安区,宽甸满族自治县,东港市,凤城市";
var CountyCodes001012001="001012001001,001012001002,001012001003,001012001004,001012001005,001012001006";
var CountyNames001012002="和平区,沈河区,大东区,皇姑区,铁西 区,苏家屯区,东陵区,新城子区,于洪区,辽中县,康平县,法库县,新民市";
var CountyCodes001012002="001012002001,001012002002,001012002003,001012002004,001012002005,001012002006,001012002007,001012002008,001012002009,001012002010,001012002011,001012002012,001012002013";
var CountyNames001012003="中山区,西岗区,沙河口区,甘井子区,旅顺口区,金州区,长海县,瓦房店市,普兰店市,庄河市";
var CountyCodes001012003="001012003001,001012003002,001012003003,001012003004,001012003005,001012003006,001012003007,001012003008,001012003009,001012003010";
var CountyNames001012004="铁东 区,铁 西 区,立山区,千山区,台安县,岫岩满族自治县,海城市";
var CountyCodes001012004="001012004001,001012004002,001012004003,001012004004,001012004005,001012004006,001012004007";
var CountyNames001012005="平山区,溪湖区,明山区,南芬区,本溪满族自治县,桓仁满族自治县";
var CountyCodes001012005="001012005001,001012005002,001012005003,001012005004,001012005005,001012005006";
var CountyNames001013001="瑶海区,庐阳区,蜀山区,包河区,长丰县,肥东县,肥西县";
var CountyCodes001013001="001013001001,001013001002,001013001003,001013001004,001013001005,001013001006,001013001007";
var CountyNames001013002="宣州区,郎溪县,广德县,泾　县,绩溪县,旌德县,宁国市";
var CountyCodes001013002="001013002001,001013002002,001013002003,001013002004,001013002005,001013002006,001013002007";
var CountyNames001013003="屯溪区,黄山区,徽州区,歙　县,休宁县,黟　县,祁门县";
var CountyCodes001013003="001013003001,001013003002,001013003003,001013003004,001013003005,001013003006,001013003007";
var CountyNames001013004="龙子湖区,蚌山区,禹会区,淮上区,怀远县,五河县,固镇县";
var CountyCodes001013004="001013004001,001013004002,001013004003,001013004004,001013004005,001013004006,001013004007";
var CountyNames001014001="台江区,仓山区,马尾区,晋安区,闽侯县,连江县,罗源县,闽清县,永泰县,平潭县,福清市,长乐市";
var CountyCodes001014001="001014001001,001014001002,001014001003,001014001004,001014001005,001014001006,001014001007,001014001008,001014001009,001014001010,001014001011,001014001012";
var CountyNames001014002="思明区,海沧区,湖里区,集美区,同安区,翔安区";
var CountyCodes001014002="001014002001,001014002002,001014002003,001014002004,001014002005,001014002006";
var CountyNames001014003="延平区,顺昌县,浦城县,光泽县,松溪县,政和县,邵武市,武夷山市,建瓯市,建阳市";
var CountyCodes001014003="001014003001,001014003002,001014003003,001014003004,001014003005,001014003006,001014003007,001014003008,001014003009,001014003010";
var CountyNames001014004="鲤城区,丰泽区,洛江区,泉港区,惠安县,安溪县,永春县,德化县,金门县,石狮市,晋江市,南安市";
var CountyCodes001014004="001014004001,001014004002,001014004003,001014004004,001014004005,001014004006,001014004007,001014004008,001014004009,001014004010,001014004011,001014004012";
var CountyNames001015001="城关 区,七里河区,西固区,安宁区,红古区,永登县,皋兰县,榆中县";
var CountyCodes001015001="001015001001,001015001002,001015001003,001015001004,001015001005,001015001006,001015001007,001015001008";
var CountyNames001015002="肃州区,金塔县,安西县,肃北蒙古族自治县,阿克塞哈萨克族自治县,玉门市,敦煌市";
var CountyCodes001015002="001015002001,001015002002,001015002003,001015002004,001015002005,001015002006,001015002007";
var CountyNames001015003="秦城区,北道区,清水县,秦安县,甘谷县,武山县,张家川回族自治县";
var CountyCodes001015003="001015003001,001015003002,001015003003,001015003004,001015003005,001015003006,001015003007";
var CountyNames001016001="东湖区,西 湖区,青云谱区,湾里区,青山湖区,南昌县,新建县,安义县,进贤县";
var CountyCodes001016001="001016001001,001016001002,001016001003,001016001004,001016001005,001016001006,001016001007,001016001008,001016001009";
var CountyNames001016002="昌江区,珠山区,浮梁县,乐平市";
var CountyCodes001016002="001016002001,001016002002,001016002003,001016002004";
var CountyNames001016003="修水县,永修县,德安县,星子县,都昌县,湖口县,彭泽县,瑞昌市,庐山区,浔阳区,九江县,武宁县";
var CountyCodes001016003="001016003005,001016003006,001016003007,001016003008,001016003009,001016003010,001016003011,001016003012,001016003001,001016003002,001016003003,001016003004";
var CountyNames001016004="章贡区,赣　县,信丰县,大余县,上犹县,崇义县,安远县,龙南县,定南县,全南县,宁都县,于都县,兴国县,会昌县,寻乌县,石城县,瑞金市,南康市";
var CountyCodes001016004="001016004001,001016004002,001016004003,001016004004,001016004005,001016004006,001016004007,001016004008,001016004009,001016004010,001016004011,001016004012,001016004013,001016004014,001016004015,001016004016,001016004017,001016004018";
var CountyNames001017001="五华区,盘龙区,官渡区,西山区,东川区,呈贡县,晋宁县,富民县,宜良县,石林彝族自治县,嵩明县,禄劝彝族苗族自治县,寻甸回族彝族自治县,安宁市";
var CountyCodes001017001="001017001001,001017001002,001017001003,001017001004,001017001005,001017001006,001017001007,001017001008,001017001009,001017001010,001017001011,001017001012,001017001013,001017001014";
var CountyNames001017002="古城区,玉龙纳西族自治县,永胜县,华坪县,宁蒗彝族自治县";
var CountyCodes001017002="001017002001,001017002002,001017002003,001017002004,001017002005";
var CountyNames001018001="南明区,云岩区,花溪区,乌当区,小河区,白 云区,开阳县,息烽县,修文县,清镇市";
var CountyCodes001018001="001018001001,001018001002,001018001003,001018001004,001018001005,001018001006,001018001007,001018001008,001018001009,001018001010";
var CountyNames001019001="锦江区,青羊区,金牛区,武侯区,成华区,龙泉驿区,青白江区,新都区,温江区,金堂县,双流县,郫　县,大邑县,蒲江县,新津县,都江堰市,彭州市,邛崃市,崇州市";
var CountyCodes001019001="001019001001,001019001002,001019001003,001019001004,001019001005,001019001006,001019001007,001019001008,001019001009,001019001010,001019001011,001019001012,001019001013,001019001014,001019001015,001019001016,001019001017,001019001018,001019001019";
var CountyNames001021001="新城 区,碑林区,莲湖区,灞桥区,未央区,雁塔区,阎良区,临潼区,长安 区,蓝田县,周至县,户　县,高陵县";
var CountyCodes001021001="001021001001,001021001002,001021001003,001021001004,001021001005,001021001006,001021001007,001021001008,001021001009,001021001010,001021001011,001021001012,001021001013";
var CountyNames001021002="宝塔区,延长县,延川县,子长县,安塞县,志丹县,吴旗县,甘泉县,富　县,洛川县,宜川县,黄龙县,黄陵县";
var CountyCodes001021002="001021002001,001021002002,001021002003,001021002004,001021002005,001021002006,001021002007,001021002008,001021002009,001021002010,001021002011,001021002012,001021002013";
var CountyNames001021003="秦都区,杨凌区,渭城区,三原县,泾阳县,乾　县,礼泉县,永寿县,彬　县,长武县,旬邑县,淳化县,武功县,兴平市";
var CountyCodes001021003="001021003001,001021003002,001021003003,001021003004,001021003005,001021003006,001021003007,001021003008,001021003009,001021003010,001021003011,001021003012,001021003013,001021003014";
var CountyNames001021004="凤翔县,岐山县,扶风县,眉　县,陇　县,千阳县,麟游县,凤　县,太白县,渭滨区,金台区,陈仓区";
var CountyCodes001021004="001021004004,001021004005,001021004006,001021004007,001021004008,001021004009,001021004010,001021004011,001021004012,001021004001,001021004002,001021004003";
var CountyNames001022001="洮北区,镇赉县,通榆县,洮南市,大安市";
var CountyCodes001022001="001022001001,001022001002,001022001003,001022001004,001022001005";
var CountyNames001022002="八道江区,抚松县,靖宇县,长白朝鲜族自治县,江源县,临江市";
var CountyCodes001022002="001022002001,001022002002,001022002003,001022002004,001022002005,001022002006";
var CountyNames001023001="原州区,西吉县,隆德县,泾源县,彭阳县";
var CountyCodes001023001="001023001001,001023001002,001023001003,001023001004,001023001005";
var CountyNames001024001="五指山市,琼海市,儋州市,文昌市,万宁市,东方市,定安县,屯昌县,澄迈县,临高县,白沙黎族自治县,昌江黎族自治县,乐东黎族自治县,陵水黎族自治县,保亭黎族苗族自治县,琼中黎族苗族自治县,西沙群岛,南沙群岛,中沙群岛的岛礁及其海域";
var CountyCodes001024001="001024001001,001024001002,001024001003,001024001004,001024001005,001024001006,001024001007,001024001008,001024001009,001024001010,001024001011,001024001012,001024001013,001024001014,001024001015,001024001016,001024001017,001024001018,001024001019";
var CountyNames001024002="秀英区,龙华区,琼山区,美兰区";
var CountyCodes001024002="001024002001,001024002002,001024002003,001024002004";
var CountyNames001026001="城关区,林周县,当雄县,尼木县,曲水县,堆龙德庆县,达孜县,墨竹工卡县";
var CountyCodes001026001="001026001001,001026001002,001026001003,001026001004,001026001005,001026001006,001026001007,001026001008";
var CountyNames001027001="海拉尔区,阿荣旗,莫力达瓦达斡尔族自治旗,鄂伦春自治旗,鄂温克族自治旗,陈巴尔虎旗,新巴尔虎左旗,新巴尔虎右旗,满洲里市,牙克石市,扎兰屯市,额尔古纳市,根河市";
var CountyCodes001027001="001027001001,001027001002,001027001003,001027001004,001027001005,001027001006,001027001007,001027001008,001027001009,001027001010,001027001011,001027001012,001027001013";
var CountyNames001027002="科尔沁区,科尔沁左翼中旗,科尔沁左翼后旗,开鲁县,库伦旗,奈曼旗,扎鲁特旗,霍林郭勒市";
var CountyCodes001027002="001027002001,001027002002,001027002003,001027002004,001027002005,001027002006,001027002007,001027002008";
var CountyNames001027003="东河区,昆都仑区,青山区,石拐区,白云矿区,九原区,土默特右旗,固阳县,达尔罕茂明安联合旗";
var CountyCodes001027003="001027003001,001027003002,001027003003,001027003004,001027003005,001027003006,001027003007,001027003008,001027003009";
var CountyNames001028001="天山区,沙依巴克区,新市区,水磨沟区,头屯河区,达坂城区,乌鲁木齐县,东山";
var CountyCodes001028001="001028001001,001028001002,001028001003,001028001004,001028001005,001028001006,001028001007,001028001008";
var CountyNames001001005="吴兴区,南浔区,长兴县,德清县,安吉县";
var CountyCodes001001005="001001005004,001001005005,001001005001,001001005002,001001005003";
var CountyNames001001006="越城区,诸暨市,上虞市,嵊州市,绍兴县,新昌市";
var CountyCodes001001006="001001006006,001001006001,001001006002,001001006003,001001006004,001001006005";
var CountyNames001001007="婺城区,金东区,兰溪市,义乌市,东阳市,永康市,武义县,浦江县,磐安县";
var CountyCodes001001007="001001007008,001001007009,001001007001,001001007002,001001007003,001001007004,001001007005,001001007006,001001007007";
var CountyNames001001008="柯城区,江山市,衢江区,龙游县,常山县,开化县";
var CountyCodes001001008="001001008006,001001008001,001001008002,001001008003,001001008004,001001008005";
var CountyNames001001009="椒江区,黄岩区,路桥区,临海市,温岭市,玉环县,天台县,仙居县,三门县";
var CountyCodes001001009="001001009007,001001009008,001001009009,001001009001,001001009002,001001009003,001001009004,001001009005,001001009006";
var CountyNames001001010="定海区,普陀区,岱山县,嵊泗县";
var CountyCodes001001010="001001010003,001001010004,001001010001,001001010002";
var CountyNames001001011="莲都区,龙泉市,缙云县,青田县,云和县,遂昌县,松阳县,庆元县,景宁畲族自治县";
var CountyCodes001001011="001001011009,001001011001,001001011002,001001011003,001001011004,001001011005,001001011006,001001011007,001001011008";
var CountyNames001002001="沧浪区,平江区,金阊区,虎丘区,相城区,吴中区,常熟市,张家港市,太仓市,昆山市,吴江市";
var CountyCodes001002001="001002001007,001002001008,001002001009,001002001010,001002001011,001002001001,001002001002,001002001003,001002001004,001002001005,001002001006";
var CountyNames001002002="清河区,清浦区,楚州区,淮阴区,涟水县,洪泽县,金湖县,盱眙县";
var CountyCodes001002002="001002002007,001002002008,001002002001,001002002002,001002002003,001002002004,001002002005,001002002006";
var CountyNames001002003="惠山区,崇安区,南长区,北塘区,滨湖区,锡山区,江阴市,宜兴市";
var CountyCodes001002003="001002003004,001002003005,001002003006,001002003007,001002003008,001002003001,001002003002,001002003003";
var CountyNames001002004="玄武区,白下区,秦淮区,建邺区,鼓楼区,下关区,栖霞区,雨花台区,江宁区,溧水县,高淳县,六合区,浦口区";
var CountyCodes001002004="001002004006,001002004007,001002004008,001002004009,001002004010,001002004011,001002004012,001002004013,001002004001,001002004002,001002004003,001002004004,001002004005";
var CountyNames001003001="市中区,任城区,曲阜市,兖州市,邹城市,鱼台县,金乡县,嘉祥县,微山县,汶上县,泗水县,梁山县";
var CountyCodes001003001="001003001011,001003001012,001003001001,001003001002,001003001003,001003001004,001003001005,001003001006,001003001007,001003001008,001003001009,001003001010";
var CountyNames001003002="黄岛区,崂山区,李沧区,城阳区,四方区,胶南市,胶州市,平度市,莱西市,即墨市,市南区,市北区";
var CountyCodes001003002="001003002009,001003002010,001003002011,001003002012,001003002008,001003002001,001003002002,001003002003,001003002004,001003002005,001003002006,001003002007";
var CountyNames001003003="泰山区,岱岳区,新泰市,肥城市,宁阳县,东平县";
var CountyCodes001003003="001003003005,001003003006,001003003001,001003003002,001003003003,001003003004";
var CountyNames001003004="德城区,乐陵市,禹城市,陵县,宁津县,齐河县,武城县,庆云县,平原县,夏津县,临邑县";
var CountyCodes001003004="001003004011,001003004001,001003004002,001003004003,001003004004,001003004005,001003004006,001003004007,001003004008,001003004009,001003004010";
var CountyNames001003005="兰山区,罗庄区,河东区,沂南县,郯城县,沂水县,苍山县,费县,平邑县,莒南县,蒙阴县,临沭县";
var CountyCodes001003005="001003005010,001003005011,001003005012,001003005001,001003005002,001003005003,001003005004,001003005005,001003005006,001003005007,001003005008,001003005009";
var CountyNames001003006="芝罘区,福山区,牟平区,莱山区,龙口市,莱阳市,莱州市,招远县,蓬莱市,栖霞市,海阳市,长岛县";
var CountyCodes001003006="001003006009,001003006010,001003006011,001003006012,001003006001,001003006002,001003006003,001003006004,001003006005,001003006006,001003006007,001003006008";
var CountyNames001003007="环翠区,乳山市,文登市,荣成市";
var CountyCodes001003007="001003007004,001003007001,001003007002,001003007003";
var CountyNames001003008="历下区,槐荫区,天桥区,历城区,章丘市,长清区,济阳县,平阴县,商河县";
var CountyCodes001003008="001003008006,001003008007,001003008008,001003008009,001003008001,001003008002,001003008003,001003008004,001003008005";
var CountyNames001004001="小店区,迎泽区,杏花岭区,尖草坪区,万柏林区,晋源区,清徐县,阳曲县,娄烦县,古交市";
var CountyCodes001004001="001004001001,001004001002,001004001003,001004001004,001004001005,001004001006,001004001007,001004001008,001004001009,001004001010";
var CountyNames001004002="南郊区,新荣区,阳高县,天镇县,广灵县,灵丘县,浑源县,左云县,大同县";
var CountyCodes001004002="001004002003,001004002004,001004002005,001004002006,001004002007,001004002008,001004002009,001004002010,001004002011";
var CountyNames001004003="忻府区,定襄县,五台县,代县,繁峙县,宁武县,静乐县,神池县,五寨县,岢岚县,河曲县,保德县,偏关县,原平市";
var CountyCodes001004003="001004003001,001004003002,001004003003,001004003004,001004003005,001004003006,001004003007,001004003008,001004003009,001004003010,001004003011,001004003012,001004003013,001004003014";
var CountyNames001005001="龙亭区,顺河回族区,南关区,杞县,通许县,尉氏县,开封县,兰考县";
var CountyCodes001005001="001005001001,001005001002,001005001003,001005001004,001005001005,001005001006,001005001007,001005001008";
var CountyNames001005002="驿城区,西平县,上蔡县,平舆县,正阳县,确山县,泌阳县,汝南县,遂平县,新蔡县";
var CountyCodes001005002="001005002001,001005002002,001005002003,001005002004,001005002005,001005002006,001005002007,001005002008,001005002009,001005002010";
var CountyNames001005003="魏都区,许昌县,鄢陵县,襄城县,禹州市,长葛市";
var CountyCodes001005003="001005003001,001005003002,001005003003,001005003004,001005003005,001005003006";
var CountyNames001005004="中原区,二七区,管城回族区,金水区,上街区,惠济区,中牟县,巩义市,荥阳市,新密市,新郑市,登封市";
var CountyCodes001005004="001005004001,001005004002,001005004003,001005004004,001005004005,001005004006,001005004007,001005004008,001005004009,001005004010,001005004011,001005004012";
var CountyNames001005005="太康县,鹿邑县,项城市,川汇区,扶沟县,西华县,商水县,沈丘县,郸城县,淮阳县";
var CountyCodes001005005="001005005008,001005005009,001005005010,001005005001,001005005002,001005005003,001005005004,001005005005,001005005006,001005005007";
var CountyNames001005006="华龙区,清丰县,南乐县,范县,台前县,濮阳县";
var CountyCodes001005006="001005006001,001005006002,001005006003,001005006004,001005006005,001005006006";
var CountyNames001005007="新华区,卫东区,石龙区,湛河区,宝丰县,叶县,鲁山县,郏县,舞钢市,汝州市";
var CountyCodes001005007="001005007001,001005007002,001005007003,001005007004,001005007005,001005007006,001005007007,001005007008,001005007009,001005007010";
var CountyNames001005008="老城区,西工区,廛河回族区,涧西区,吉利区,洛龙区,孟津县,新安县,栾川县,嵩县,汝阳县,宜阳县,洛宁县,伊川县,偃师市";
var CountyCodes001005008="001005008001,001005008002,001005008003,001005008004,001005008005,001005008006,001005008007,001005008008,001005008009,001005008010,001005008011,001005008012,001005008013,001005008014,001005008015";
var CountyNames001005009=""
var CountyCodes001005009=""
var CountyNames001005010="源汇区,郾城区,召陵区,舞阳县,临颍县";
var CountyCodes001005010="001005010001,001005010002,001005010003,001005010004,001005010005";
var CountyNames001005011="鹤山区,山城区,淇滨区,浚县,淇县";
var CountyCodes001005011="001005011001,001005011002,001005011003,001005011004,001005011005";
var CountyNames001005012="宛城区,卧龙区,南召县,方城县,西峡县,镇平县,内乡县,淅川县,社旗县,唐河县,新野县,桐柏县,邓州市";
var CountyCodes001005012="001005012001,001005012002,001005012003,001005012004,001005012005,001005012006,001005012007,001005012008,001005012009,001005012010,001005012011,001005012012,001005012013";
var CountyNames001005013="湖滨区,渑池县,陕　县,卢氏县,义马市,灵宝市";
var CountyCodes001005013="001005013001,001005013002,001005013003,001005013004,001005013005,001005013006";
var CountyNames001005014="文峰区,北关区,殷都区,龙安区,安阳县,汤阴县,滑　县,内黄县,林州市";
var CountyCodes001005014="001005014001,001005014002,001005014003,001005014004,001005014005,001005014006,001005014007,001005014008,001005014009";
var CountyNames001005015="浉河区,平桥区,罗山县,光山县,新　县,商城县,固始县,潢川县,淮滨县,息　县";
var CountyCodes001005015="001005015001,001005015002,001005015003,001005015004,001005015005,001005015006,001005015007,001005015008,001005015009,001005015010";
var CountyNames001005016="梁园区,睢阳区,民权县,睢　县,宁陵县,柘城县,虞城县,夏邑县,永城市";
var CountyCodes001005016="001005016001,001005016002,001005016003,001005016004,001005016005,001005016006,001005016007,001005016008,001005016009";
var CountyNames001006001="海港区,山海关区,北戴河区,青龙满族自治县,昌黎县,抚宁县,卢龙县";
var CountyCodes001006001="001006001001,001006001002,001006001003,001006001004,001006001005,001006001006,001006001007";
var CountyNames001006002="曲周县,武安市,临漳县,成安县,大名县,涉　县,磁　县,肥乡县,永年县,邱　县,鸡泽县,广平县,馆陶县,魏　县,邯山区,丛台区,复兴区,峰峰矿区,邯郸县";
var CountyCodes001006002="001006002018,001006002019,001006002006,001006002007,001006002008,001006002009,001006002010,001006002011,001006002012,001006002013,001006002014,001006002015,001006002016,001006002017,001006002001,001006002002,001006002003,001006002004,001006002005";
var CountyNames001006003="路南区,路北区,古冶区,开平区,丰南区,丰润区,滦　县,滦南县,乐亭县,迁西县,玉田县,唐海县,遵化市,迁安市";
var CountyCodes001006003="001006003001,001006003002,001006003003,001006003004,001006003005,001006003006,001006003007,001006003008,001006003009,001006003010,001006003011,001006003012,001006003013,001006003014";
var CountyNames001006004="双桥 区,双滦区,鹰手营子矿区,承德县,兴隆县,平泉县,滦平县,隆化县,丰宁满族自治县,宽城满族自治县,围场满族蒙古族自治县";
var CountyCodes001006004="001006004001,001006004002,001006004003,001006004004,001006004005,001006004006,001006004007,001006004008,001006004009,001006004010,001006004011";
var CountyNames001006005="桥 东区,桥 西区,宣化区,下花园区,宣化县,张北县,康保县,沽源县,尚义县,蔚　县,阳原县,怀安县,万全县,怀来县,涿鹿县,赤城县,崇礼县";
var CountyCodes001006005="001006005001,001006005002,001006005003,001006005004,001006005005,001006005006,001006005007,001006005008,001006005009,001006005010,001006005011,001006005012,001006005013,001006005014,001006005015,001006005016,001006005017";
var CountyNames001006006="新市 区,北市区,南市区,满城县,清苑县,涞水县,阜平县,徐水县,定兴县,唐　县,高阳县,容城县,涞源县,望都县,安新县,易　县,曲阳县,蠡　县,顺平县,博野县,雄　县,涿州市,定州市,安国市,高碑店市";
var CountyCodes001006006="001006006001,001006006002,001006006003,001006006004,001006006005,001006006006,001006006007,001006006008,001006006009,001006006010,001006006011,001006006012,001006006013,001006006014,001006006015,001006006016,001006006017,001006006018,001006006019,001006006020,001006006021,001006006022,001006006023,001006006024,001006006025";
var CountyNames001006007="长安区,桥东 区,桥西 区,新 华区,井陉矿区,裕华区,井陉县,正定县,栾城县,行唐县,灵寿县,高邑县,深泽县,赞皇县,无极县,平山县,元氏县,赵　县,辛集市,藁城市,晋州市,新乐市,鹿泉市";
var CountyCodes001006007="001006007001,001006007002,001006007003,001006007004,001006007005,001006007006,001006007007,001006007008,001006007009,001006007010,001006007011,001006007012,001006007013,001006007014,001006007015,001006007016,001006007017,001006007018,001006007019,001006007020,001006007021,001006007022,001006007023";
var CountyNames001007001="芙蓉区,天心区,岳麓区,开福区,雨花区,长沙县,望城县,宁乡县,浏阳市";
var CountyCodes001007001="001007001001,001007001002,001007001003,001007001004,001007001005,001007001006,001007001007,001007001008,001007001009";
var CountyNames001007002="武陵区,鼎城区,安乡县,汉寿县,澧　县,临澧县,桃源县,石门县,津市市";
var CountyCodes001007002="001007002001,001007002002,001007002003,001007002004,001007002005,001007002006,001007002007,001007002008,001007002009";
var CountyNames001007003="珠晖区,雁峰区,石鼓区,蒸湘区,南岳区,衡阳县,衡南县,衡山县,衡东县,祁东县,耒阳市,常宁市";
var CountyCodes001007003="001007003001,001007003002,001007003003,001007003004,001007003005,001007003006,001007003007,001007003008,001007003009,001007003010,001007003011,001007003012";
var CountyNames001007004="岳阳楼区,云溪区,君山区,岳阳县,华容县,湘阴县,平江县,汨罗市,临湘市";
var CountyCodes001007004="001007004001,001007004002,001007004003,001007004004,001007004005,001007004006,001007004007,001007004008,001007004009";
var CountyNames001007005="永定区,武陵源区,慈利县,桑植县";
var CountyCodes001007005="001007005001,001007005002,001007005003,001007005004";
var CountyNames001007006="雨湖区,岳塘区,湘潭县,湘乡市,韶山市";
var CountyCodes001007006="001007006001,001007006002,001007006003,001007006004,001007006005";
var CountyNames001008001="西陵区,伍家岗区,点军区,猇亭区,夷陵区,远安县,兴山县,秭归县,长阳土家族自治县,五峰土家族自治县,宜都市,当阳市,枝江市";
var CountyCodes001008001="001008001001,001008001002,001008001003,001008001004,001008001005,001008001006,001008001007,001008001008,001008001009,001008001010,001008001011,001008001012,001008001013";
var CountyNames001008002="江岸区,江汉区,硚口区,汉阳区,武昌区,青山 区,洪山区,东西湖区,汉南区,蔡甸区,江夏区,黄陂区,新洲区";
var CountyCodes001008002="001008002001,001008002002,001008002003,001008002004,001008002005,001008002006,001008002007,001008002008,001008002009,001008002010,001008002011,001008002012,001008002013";
var CountyNames001008003="黄州区,团风县,红安县,罗田县,英山县,浠水县,蕲春县,黄梅县,麻城市,武穴市";
var CountyCodes001008003="001008003001,001008003002,001008003003,001008003004,001008003005,001008003006,001008003007,001008003008,001008003009,001008003010";
var CountyNames001008004="茅箭区,张湾区,郧　县,郧西县,竹山县,竹溪县,房　县,丹江口市";
var CountyCodes001008004="001008004001,001008004002,001008004003,001008004004,001008004005,001008004006,001008004007,001008004008";
var CountyNames001009001="罗湖区,福田区,南山区,宝安区,龙岗区,盐田区";
var CountyCodes001009001="001009001001,001009001002,001009001003,001009001004,001009001005,001009001006";
var CountyNames001009002="禅城区,南海区,顺德区,三水区,高明区";
var CountyCodes001009002="001009002001,001009002002,001009002003,001009002004,001009002005";
var CountyNames001009003="源城区,紫金县,龙川县,连平县,和平县,东源县";
var CountyCodes001009003="001009003001,001009003002,001009003003,001009003004,001009003005,001009003006";
var CountyNames001009004="蓬江区,江海区,新会区,台山市,开平市,鹤山市,恩平市";
var CountyCodes001009004="001009004001,001009004002,001009004003,001009004004,001009004005,001009004006,001009004007";
var CountyNames001009005="江城区,阳西县,阳东县,阳春市";
var CountyCodes001009005="001009005001,001009005002,001009005003,001009005004";
var CountyNames001009006="龙湖区,金平区,濠江区,潮阳区,潮南区,澄海区";
var CountyCodes001009006="001009006001,001009006002,001009006003,001009006004,001009006005,001009006006";
var CountyNames001009007="梅州区,梅县,大埔县,丰顺县,五华县,平远县,蕉岭县,兴宁市";
var CountyCodes001009007="001009007001,001009007002,001009007003,001009007004,001009007005,001009007006,001009007007,001009007008";
var CountyNames001009008="云城区,新兴县,郁南县,云安县,罗定市";
var CountyCodes001009008="001009008001,001009008002,001009008003,001009008004,001009008005";
var CountyNames001009009="武江区,浈江区,曲江区,始兴县,仁化县,翁源县,乳源瑶族自治县,新丰县,乐昌市,南雄市";
var CountyCodes001009009="001009009001,001009009002,001009009003,001009009004,001009009005,001009009006,001009009007,001009009008,001009009009,001009009010";
var CountyNames001009010="城区,海丰县,陆河县,陆丰市";
var CountyCodes001009010="001009010001,001009010002,001009010003,001009010004";
var CountyNames001009011="端州区,鼎湖区,广宁县,怀集县,封开县,德庆县,高要市,四会市";
var CountyCodes001009011="001009011001,001009011002,001009011003,001009011004,001009011005,001009011006,001009011007,001009011008";
var CountyNames001009012=""
var CountyCodes001009012=""
var CountyNames001009013="香洲区,斗门区,金湾区";
var CountyCodes001009013="001009013001,001009013002,001009013003";
var CountyNames001009014="湘桥区,潮安县,饶平县";
var CountyCodes001009014="001009014001,001009014002,001009014003";
var CountyNames001009015="清城区,佛冈县,阳山县,连山壮族瑶族自治县,连南瑶族自治县,清新县,英德市,连州市";
var CountyCodes001009015="001009015001,001009015002,001009015003,001009015004,001009015005,001009015006,001009015007,001009015008";
var CountyNames001008012="孝南区,孝昌县,大悟县,云梦县,应城市,安陆市,汉川市";
var CountyCodes001008012="001008012001,001008012002,001008012003,001008012004,001008012005,001008012006,001008012007";
var CountyNames001008013="曾都区,广水市";
var CountyCodes001008013="001008013001,001008013002";
var CountyNames001009016="东山区,荔湾区,越秀区,海珠区,天河区,芳村区,白云区,黄埔区,番禺区,花都区,增城市,从化市";
var CountyCodes001009016="001009016001,001009016002,001009016003,001009016004,001009016005,001009016006,001009016007,001009016008,001009016009,001009016010,001009016011,001009016012";
var CountyNames001009017=""
var CountyCodes001009017=""
var CountyNames001009018="博罗县,惠东县,龙门县,惠城区,惠阳区";
var CountyCodes001009018="001009018003,001009018004,001009018005,001009018001,001009018002";
var CountyNames001009019="榕城区,揭东县,揭西县,惠来县,普宁市";
var CountyCodes001009019="001009019001,001009019002,001009019003,001009019004,001009019005";
var CountyNames001009020="茂港区,电白县,高州市,化州市,信宜市,茂南区";
var CountyCodes001009020="001009020002,001009020003,001009020004,001009020005,001009020006,001009020001";
var CountyNames001009021="赤坎区,霞山区,坡头区,麻章区,遂溪县,徐闻县,廉江市,雷州市,吴川市";
var CountyCodes001009021="001009021001,001009021002,001009021003,001009021004,001009021005,001009021006,001009021007,001009021008,001009021009";
var CountyNames001010001="秀峰区,叠彩区,象山区,七星区,雁山区,阳朔县,临桂县,灵川县,全州县,兴安县,永福县,灌阳县,龙胜各族自治县,资源县,平乐县,荔蒲县,恭城瑶族自治县";
var CountyCodes001010001="001010001001,001010001002,001010001003,001010001004,001010001005,001010001006,001010001007,001010001008,001010001009,001010001010,001010001011,001010001012,001010001013,001010001014,001010001015,001010001016,001010001017";
var CountyNames001010002="兴宁区,青秀区,江南区,西乡塘区,良庆区,邕宁区,武鸣县,隆安县,马山县,上林县,宾阳县,横　县";
var CountyCodes001010002="001010002001,001010002002,001010002003,001010002004,001010002005,001010002006,001010002007,001010002008,001010002009,001010002010,001010002011,001010002012";
var CountyNames001010006="右江区,田阳县,田东县,平果县,德保县,靖西县,那坡县,凌云县,乐业县,田林县,西林县,隆林各族自治县";
var CountyCodes001010006="001010006001,001010006002,001010006003,001010006004,001010006005,001010006006,001010006007,001010006008,001010006009,001010006010,001010006011,001010006012";
var CountyNames001010007="金城江区,南丹县,天峨县,凤山县,东兰县,罗城仫佬族自治县,环江毛南族自治县,巴马瑶族自治县,都安瑶族自治县,大化瑶族自治县,宜州市";
var CountyCodes001010007="001010007001,001010007002,001010007003,001010007004,001010007005,001010007006,001010007007,001010007008,001010007009,001010007010,001010007011";
var CountyNames001010008="万秀区,蝶山区,长洲区,苍梧县,藤　县,蒙山县,岑溪市";
var CountyCodes001010008="001010008001,001010008002,001010008003,001010008004,001010008005,001010008006,001010008007";
var CountyNames001010009="玉州区,容　县,陆川县,博白县,兴业县,北流市";
var CountyCodes001010009="001010009001,001010009002,001010009003,001010009004,001010009005,001010009006";
var CountyNames001010010="港口区,防城区,上思县,东兴市";
var CountyCodes001010010="001010010001,001010010002,001010010003,001010010004";
var CountyNames001010011="港北区,港南区,覃塘区,平南县,桂平市";
var CountyCodes001010011="001010011001,001010011002,001010011003,001010011004,001010011005";
var CountyNames001010012="江洲区,扶绥县,宁明县,龙州县,大新县,天等县,凭祥市";
var CountyCodes001010012="001010012001,001010012002,001010012003,001010012004,001010012005,001010012006,001010012007";
var CountyNames001010013="金秀瑶族自治县,合山市,兴宾区,忻城县,象州县,武宣县";
var CountyCodes001010013="001010013005,001010013006,001010013001,001010013002,001010013003,001010013004";
var CountyNames001011004="向阳区,工农区,南山 区,兴安区,东山 区,兴山区,萝北县,绥滨县";
var CountyCodes001011004="001011004001,001011004002,001011004003,001011004004,001011004005,001011004006,001011004007,001011004008";
var CountyNames001011005="永红区,向阳 区,前进区,东风区,郊区,桦南县,桦川县,汤原县,抚远县,同江市,富锦市";
var CountyCodes001011005="001011005001,001011005002,001011005003,001011005004,001011005005,001011005006,001011005007,001011005008,001011005009,001011005010,001011005011";
var CountyNames001011006="鸡冠区,恒山区,滴道区,梨树区,城子河区,麻山区,鸡东县,虎林市,密山市";
var CountyCodes001011006="001011006001,001011006002,001011006003,001011006004,001011006005,001011006006,001011006007,001011006008,001011006009";
var CountyNames001011007="阳明区,爱民区,西安 区,东宁县,林口县,绥芬河市,海林市,宁安市,穆棱市,东安区";
var CountyCodes001011007="001011007002,001011007003,001011007004,001011007005,001011007006,001011007007,001011007008,001011007009,001011007010,001011007001";
var CountyNames001011008="龙沙区,建华区,铁锋区,昂昂溪区,富拉尔基区,碾子山区,梅里斯达斡尔族区,龙江县,依安县,泰来县,甘南县,富裕县,克山县,克东县,拜泉县,讷河市";
var CountyCodes001011008="001011008001,001011008002,001011008003,001011008004,001011008005,001011008006,001011008007,001011008008,001011008009,001011008010,001011008011,001011008012,001011008013,001011008014,001011008015,001011008016";
var CountyNames001011009="尖山区,岭东区,四方台区,宝山 区,集贤县,友谊县,宝清县";
var CountyCodes001011009="001011009001,001011009002,001011009003,001011009004,001011009005,001011009006,001011009007";
var CountyNames001011010="嘉荫县,铁力市,伊春区,南岔区,友好区,西林区,翠峦区,新青区,美溪区,金山屯区,五营区,乌马河区,汤旺河区,带岭区,乌伊岭区,红星区,上甘岭区";
var CountyCodes001011010="001011010016,001011010017,001011010001,001011010002,001011010003,001011010004,001011010005,001011010006,001011010007,001011010008,001011010009,001011010010,001011010011,001011010012,001011010013,001011010014,001011010015";
var CountyNames001011011="新兴区,桃山区,茄子河区,勃利县";
var CountyCodes001011011="001011011001,001011011002,001011011003,001011011004";
var CountyNames001011012="北林区,望奎县,兰西县,青冈县,庆安县,明水县,绥棱县,安达市,肇东市,海伦市";
var CountyCodes001011012="001011012001,001011012002,001011012003,001011012004,001011012005,001011012006,001011012007,001011012008,001011012009,001011012010";
var CountyNames001011013="呼玛县,塔河县,漠河县";
var CountyCodes001011013="001011013001,001011013002,001011013003";
var CountyNames001012006="双塔区,龙城区,朝阳县,建平县,喀喇沁左翼蒙古族自治县,北票市,凌源市";
var CountyCodes001012006="001012006001,001012006002,001012006003,001012006004,001012006005,001012006006,001012006007";
var CountyNames001012007="新抚区,东洲区,望花区,顺城区,抚顺县,新宾满族自治县,清原满族自治县";
var CountyCodes001012007="001012007001,001012007002,001012007003,001012007004,001012007005,001012007006,001012007007";
var CountyNames001012008="古塔区,凌河区,太和区,黑山县,义　县,凌海市,北宁市";
var CountyCodes001012008="001012008001,001012008002,001012008003,001012008004,001012008005,001012008006,001012008007";
var CountyNames001012009="银州区,清河 区,铁岭县,西丰县,昌图县,调兵山市,开原市";
var CountyCodes001012009="001012009001,001012009002,001012009003,001012009004,001012009005,001012009006,001012009007";
var CountyNames001012010="站前区,西市区,鲅鱼圈区,老边区,盖州市,大石桥市";
var CountyCodes001012010="001012010001,001012010002,001012010003,001012010004,001012010005,001012010006";
var CountyNames001012011="连山区,龙港区,南票区,绥中县,建昌县,兴城市";
var CountyCodes001012011="001012011001,001012011002,001012011003,001012011004,001012011005,001012011006";
var CountyNames001012012="双台子区,兴隆台区,大洼县,盘山县";
var CountyCodes001012012="001012012001,001012012002,001012012003,001012012004";
var CountyNames001012013="海州 区,新邱区,太平区,清河门区,细河区,阜新蒙古族自治县,彰武县";
var CountyCodes001012013="001012013001,001012013002,001012013003,001012013004,001012013005,001012013006,001012013007";
var CountyNames001012014="白塔区,文圣区,宏伟区,弓长岭区,太子河区,辽阳县,灯塔市";
var CountyCodes001012014="001012014001,001012014002,001012014003,001012014004,001012014005,001012014006,001012014007";
var CountyNames001013005="迎江区,大观区,郊　区,怀宁县,枞阳县,潜山县,太湖县,宿松县,望江县,岳西县,桐城市";
var CountyCodes001013005="001013005001,001013005002,001013005003,001013005004,001013005005,001013005006,001013005007,001013005008,001013005009,001013005010,001013005011";
var CountyNames001013006="居巢区,庐江县,无为县,含山县,和　县";
var CountyCodes001013006="001013006001,001013006002,001013006003,001013006004,001013006005";
var CountyNames001013007="琅琊区,南谯区,来安县,全椒县,定远县,凤阳县,天长市,明光市";
var CountyCodes001013007="001013007001,001013007002,001013007003,001013007004,001013007005,001013007006,001013007007,001013007008";
var CountyNames001013008="颍州区,颍东区,颍泉区,临泉县,太和县,阜南县,颍上县,界首市";
var CountyCodes001013008="001013008001,001013008002,001013008003,001013008004,001013008005,001013008006,001013008007,001013008008";
var CountyNames001013009="杜集区,相山区,烈山区,濉溪县";
var CountyCodes001013009="001013009001,001013009002,001013009003,001013009004";
var CountyNames001013010="八公山区,潘集区,凤台县,大通区,田家庵区,谢家集区";
var CountyCodes001013010="001013010004,001013010005,001013010006,001013010001,001013010002,001013010003";
var CountyNames001013011="金家庄区,花山区,雨山区,当涂县";
var CountyCodes001013011="001013011001,001013011002,001013011003,001013011004";
var CountyNames001013012="埇桥区,砀山县,萧　县,灵璧县,泗　县";
var CountyCodes001013012="001013012001,001013012002,001013012003,001013012004,001013012005";
var CountyNames001013013="铜官山区,狮子山区,铜陵县";
var CountyCodes001013013="001013013001,001013013002,001013013003";
var CountyNames001013014="镜湖区,马塘区,新芜区,鸠江区,芜湖县,繁昌县,南陵县";
var CountyCodes001013014="001013014001,001013014002,001013014003,001013014004,001013014005,001013014006,001013014007";
var CountyNames001013015="贵池区,东至县,石台县,青阳县";
var CountyCodes001013015="001013015001,001013015002,001013015003,001013015004";
var CountyNames001013016="金寨县,霍山县,金安区,裕安区,寿　县,霍邱县,舒城县";
var CountyCodes001013016="001013016006,001013016007,001013016001,001013016002,001013016003,001013016004,001013016005";
var CountyNames001013017="谯城区,涡阳县,蒙城县,利辛县";
var CountyCodes001013017="001013017001,001013017002,001013017003,001013017004";
var CountyNames001014005="新罗区,长汀县,永定县,上杭县,武平县,连城县,漳平市";
var CountyCodes001014005="001014005001,001014005002,001014005003,001014005004,001014005005,001014005006,001014005007";
var CountyNames001014006="城厢区,涵江区,荔城区,秀屿区,仙游县";
var CountyCodes001014006="001014006001,001014006002,001014006003,001014006004,001014006005";
var CountyNames001014007="梅列区,三元区,明溪县,清流县,宁化县,大田县,尤溪县,沙　县,将乐县,泰宁县,建宁县,永安市";
var CountyCodes001014007="001014007001,001014007002,001014007003,001014007004,001014007005,001014007006,001014007007,001014007008,001014007009,001014007010,001014007011,001014007012";
var CountyNames001014008="芗城区,龙文区,云霄县,漳浦县,诏安县,长泰县,东山县,南靖县,平和县,华安县,龙海市";
var CountyCodes001014008="001014008001,001014008002,001014008003,001014008004,001014008005,001014008006,001014008007,001014008008,001014008009,001014008010,001014008011";
var CountyNames001014009="蕉城区,霞浦县,古田县,屏南县,寿宁县,周宁县,柘荣县,福安市,福鼎市";
var CountyCodes001014009="001014009001,001014009002,001014009003,001014009004,001014009005,001014009006,001014009007,001014009008,001014009009";
var CountyNames001015004=""
var CountyCodes001015004=""
var CountyNames001015005="金川区,永昌县";
var CountyCodes001015005="001015005001,001015005002";
var CountyNames001015006="临夏市,临夏县,康乐县,永靖县,广河县,和政县,东乡族自治县,积石山保安族东乡族撒拉族自治县";
var CountyCodes001015006="001015006001,001015006002,001015006003,001015006004,001015006005,001015006006,001015006007,001015006008";
var CountyNames001015007="崆峒区,泾川县,灵台县,崇信县,华亭县,庄浪县,静宁县";
var CountyCodes001015007="001015007001,001015007002,001015007003,001015007004,001015007005,001015007006,001015007007";
var CountyNames001015008="凉州区,民勤县,古浪县,天祝藏族自治县";
var CountyCodes001015008="001015008001,001015008002,001015008003,001015008004";
var CountyNames001015009="白银区,平川区,靖远县,会宁县,景泰县";
var CountyCodes001015009="001015009001,001015009002,001015009003,001015009004,001015009005";
var CountyNames001015010="安定区,通渭县,陇西县,渭源县,临洮县,漳　县,岷　县";
var CountyCodes001015010="001015010001,001015010002,001015010003,001015010004,001015010005,001015010006,001015010007";
var CountyNames001015011="甘州区,肃南裕固族自治县,民乐县,临泽县,高台县,山丹县";
var CountyCodes001015011="001015011001,001015011002,001015011003,001015011004,001015011005,001015011006";
var CountyNames001015012="西峰区,庆城县,环　县,华池县,合水县,正宁县,宁　县,镇原县";
var CountyCodes001015012="001015012001,001015012002,001015012003,001015012004,001015012005,001015012006,001015012007,001015012008";
var CountyNames001015013="武都区,成　县,文　县,宕昌县,康　县,西和县,礼　县,徽　县,两当县";
var CountyCodes001015013="001015013001,001015013002,001015013003,001015013004,001015013005,001015013006,001015013007,001015013008,001015013009";
var CountyNames001015014="合作市,临潭县,卓尼县,舟曲县,迭部县,玛曲县,碌曲县,夏河县";
var CountyCodes001015014="001015014001,001015014002,001015014003,001015014004,001015014005,001015014006,001015014007,001015014008";
var CountyNames001016005="吉州区,青原区,吉安县,吉水县,峡江县,新干县,永丰县,泰和县,遂川县,万安县,安福县,永新县,井冈山市";
var CountyCodes001016005="001016005001,001016005002,001016005003,001016005004,001016005005,001016005006,001016005007,001016005008,001016005009,001016005010,001016005011,001016005012,001016005013";
var CountyNames001016006="临川区,南城县,黎川县,南丰县,崇仁县,乐安县,宜黄县,金溪县,资溪县,东乡县,广昌县";
var CountyCodes001016006="001016006001,001016006002,001016006003,001016006004,001016006005,001016006006,001016006007,001016006008,001016006009,001016006010,001016006011";
var CountyNames001016007="安源区,湘东区,莲花县,上栗县,芦溪县";
var CountyCodes001016007="001016007001,001016007002,001016007003,001016007004,001016007005";
var CountyNames001016008="渝水区,分宜县";
var CountyCodes001016008="001016008001,001016008002";
var CountyNames001016009="信州区,上饶县,广丰县,玉山县,铅山县,横峰县,弋阳县,余干县,鄱阳县,万年县,婺源县,德兴市";
var CountyCodes001016009="001016009001,001016009002,001016009003,001016009004,001016009005,001016009006,001016009007,001016009008,001016009009,001016009010,001016009011,001016009012";
var CountyNames001016010="袁州区,奉新县,万载县,上高县,宜丰县,靖安县,铜鼓县,丰城市,樟树市,高安市";
var CountyCodes001016010="001016010001,001016010002,001016010003,001016010004,001016010005,001016010006,001016010007,001016010008,001016010009,001016010010";
var CountyNames001016011="月湖区,余江县,贵溪市";
var CountyCodes001016011="001016011001,001016011002,001016011003";
var CountyNames001017003="大理市,漾濞彝族自治县,祥云县,宾川县,弥渡县,南涧彝族自治县,巍山彝族回族自治县,永平县,云龙县,洱源县,剑川县,鹤庆县";
var CountyCodes001017003="001017003001,001017003002,001017003003,001017003004,001017003005,001017003006,001017003007,001017003008,001017003009,001017003010,001017003011,001017003012";
var CountyNames001017004="麒麟区,马龙县,陆良县,师宗县,罗平县,富源县,会泽县,沾益县,宣威市";
var CountyCodes001017004="001017004001,001017004002,001017004003,001017004004,001017004005,001017004006,001017004007,001017004008,001017004009";
var CountyNames001017005="红塔区,江川县,澄江县,通海县,华宁县,易门县,峨山彝族自治县,新平彝族傣族自治县,元江哈尼族彝族傣族自治县";
var CountyCodes001017005="001017005001,001017005002,001017005003,001017005004,001017005005,001017005006,001017005007,001017005008,001017005009";
var CountyNames001017006="昭阳区,鲁甸县,巧家县,盐津县,大关县,永善县,绥江县,镇雄县,彝良县,威信县,水富县";
var CountyCodes001017006="001017006001,001017006002,001017006003,001017006004,001017006005,001017006006,001017006007,001017006008,001017006009,001017006010,001017006011";
var CountyNames001017007="翠云区,普洱哈尼族彝族自治县,墨江哈尼族自治县,景东彝族自治县,景谷傣族彝族自治县,镇沅彝族哈尼族拉祜族自治县,江城哈尼族彝族自治县,孟连傣族拉祜族佤族自治县,澜沧拉祜族自治县,西盟佤族自治县";
var CountyCodes001017007="001017007001,001017007002,001017007003,001017007004,001017007005,001017007006,001017007007,001017007008,001017007009,001017007010";
var CountyNames001017008="临翔区,凤庆县,云　县,永德县,镇康县,双江拉祜族佤族布朗族傣族自治县,耿马傣族佤族自治县,沧源佤族自治县";
var CountyCodes001017008="001017008001,001017008002,001017008003,001017008004,001017008005,001017008006,001017008007,001017008008";
var CountyNames001017009="隆阳区,施甸县,腾冲县,龙陵县,昌宁县";
var CountyCodes001017009="001017009001,001017009002,001017009003,001017009004,001017009005";
var CountyNames001017010="文山县,砚山县,西畴县,麻栗坡县,马关县,丘北县,广南县,富宁县";
var CountyCodes001017010="001017010001,001017010002,001017010003,001017010004,001017010005,001017010006,001017010007,001017010008";
var CountyNames001017011="个旧市,开远市,蒙自县,屏边苗族自治县,建水县,石屏县,弥勒县,泸西县,元阳县,红河县,金平苗族瑶族傣族自治县,绿春县,河口瑶族自治县";
var CountyCodes001017011="001017011001,001017011002,001017011003,001017011004,001017011005,001017011006,001017011007,001017011008,001017011009,001017011010,001017011011,001017011012,001017011013";
var CountyNames001017012="景洪市,勐海县,勐腊县";
var CountyCodes001017012="001017012001,001017012002,001017012003";
var CountyNames001017013="楚雄市,双柏县,牟定县,南华县,姚安县,大姚县,永仁县,元谋县,武定县,禄丰县";
var CountyCodes001017013="001017013001,001017013002,001017013003,001017013004,001017013005,001017013006,001017013007,001017013008,001017013009,001017013010";
var CountyNames001017014="瑞丽市,潞西市,梁河县,盈江县,陇川县";
var CountyCodes001017014="001017014001,001017014002,001017014003,001017014004,001017014005";
var CountyNames001017015="泸水县,福贡县,贡山独龙族怒族自治县,兰坪白族普米族自治县";
var CountyCodes001017015="001017015001,001017015002,001017015003,001017015004";
var CountyNames001017016="香格里拉县,德钦县,维西傈僳族自治县";
var CountyCodes001017016="001017016001,001017016002,001017016003";
var CountyNames001018002="西秀区,平坝县,普定县,镇宁布依族苗族自治县,关岭布依族苗族自治县,紫云苗族布依族自治县";
var CountyCodes001018002="001018002001,001018002002,001018002003,001018002004,001018002005,001018002006";
var CountyNames001018003="毕节市,大方县,黔西县,金沙县,织金县,纳雍县,威宁彝族回族苗族自治县,赫章县";
var CountyCodes001018003="001018003001,001018003002,001018003003,001018003004,001018003005,001018003006,001018003007,001018003008";
var CountyNames001018004="铜仁市,江口县,玉屏侗族自治县,石阡县,思南县,印江土家族苗族自治县,德江县,沿河土家族自治县,松桃苗族自治县,万山特区";
var CountyCodes001018004="001018004001,001018004002,001018004003,001018004004,001018004005,001018004006,001018004007,001018004008,001018004009,001018004010";
var CountyNames001018005="兴义市,兴仁县,普安县,晴隆县,贞丰县,望谟县,册亨县,安龙县";
var CountyCodes001018005="001018005001,001018005002,001018005003,001018005004,001018005005,001018005006,001018005007,001018005008";
var CountyNames001018006="钟山区,六枝特区,水城县,盘　县";
var CountyCodes001018006="001018006001,001018006002,001018006003,001018006004";
var CountyNames001018007="红花岗区,汇川区,遵义县,桐梓县,绥阳县,正安县,道真仡佬族苗族自治县,务川仡佬族苗族自治县,凤冈县,湄潭县,余庆县,习水县,赤水市,仁怀市";
var CountyCodes001018007="001018007001,001018007002,001018007003,001018007004,001018007005,001018007006,001018007007,001018007008,001018007009,001018007010,001018007011,001018007012,001018007013,001018007014";
var CountyNames001018008="凯里市,黄平县,施秉县,三穗县,镇远县,岑巩县,天柱县,锦屏县,剑河县,台江县,黎平县,榕江县,从江县,雷山县,麻江县,丹寨县";
var CountyCodes001018008="001018008001,001018008002,001018008003,001018008004,001018008005,001018008006,001018008007,001018008008,001018008009,001018008010,001018008011,001018008012,001018008013,001018008014,001018008015,001018008016";
var CountyNames001018009="都匀市,福泉市,荔波县,贵定县,瓮安县,独山县,平塘县,罗甸县,长顺县,龙里县,惠水县,三都水族自治县";
var CountyCodes001018009="001018009001,001018009002,001018009003,001018009004,001018009005,001018009006,001018009007,001018009008,001018009009,001018009010,001018009011,001018009012";
var CountyNames001019002="旌阳区,中江县,罗江县,广汉市,什邡市,绵竹市";
var CountyCodes001019002="001019002001,001019002002,001019002003,001019002004,001019002005,001019002006";
var CountyNames001019003="元坝区,朝天区,旺苍县,青川县,剑阁县,苍溪县";
var CountyCodes001019003="001019003001,001019003002,001019003003,001019003004,001019003005,001019003006";
var CountyNames001019004="沙湾区,五通桥区,金口河区,犍为县,井研县,夹江县,沐川县,峨边彝族自治县,马边彝族自治县,峨眉山市";
var CountyCodes001019004="001019004001,001019004002,001019004003,001019004004,001019004005,001019004006,001019004007,001019004008,001019004009,001019004010";
var CountyNames001019005="江阳区,纳溪区,龙马潭区,泸　县,合江县,叙永县,古蔺县";
var CountyCodes001019005="001019005001,001019005002,001019005003,001019005004,001019005005,001019005006,001019005007";
var CountyNames001019006="涪城区,游仙区,三台县,盐亭县,安　县,梓潼县,北川羌族自治县,平武县,江油市";
var CountyCodes001019006="001019006001,001019006002,001019006003,001019006004,001019006005,001019006006,001019006007,001019006008,001019006009";
var CountyNames001019007="顺庆区,高坪区,嘉陵区,南部县,营山县,蓬安县,仪陇县,西充县,阆中市";
var CountyCodes001019007="001019007001,001019007002,001019007003,001019007004,001019007005,001019007006,001019007007,001019007008,001019007009";
var CountyNames001019008="东兴区,威远县,资中县,隆昌县";
var CountyCodes001019008="001019008001,001019008002,001019008003,001019008004";
var CountyNames001019009="东　区,西　区,仁和区,米易县,盐边县";
var CountyCodes001019009="001019009001,001019009002,001019009003,001019009004,001019009005";
var CountyNames001019010="船山区,安居区,蓬溪县,射洪县,大英县";
var CountyCodes001019010="001019010001,001019010002,001019010003,001019010004,001019010005";
var CountyNames001019011="翠屏区,宜宾县,南溪县,江安县,长宁县,高　县,珙　县,筠连县,兴文县,屏山县";
var CountyCodes001019011="001019011001,001019011002,001019011003,001019011004,001019011005,001019011006,001019011007,001019011008,001019011009,001019011010";
var CountyNames001019012="自流井区,贡井区,大安区,沿滩区,荣　县,富顺县";
var CountyCodes001019012="001019012001,001019012002,001019012003,001019012004,001019012005,001019012006";
var CountyNames001019013="广安区,岳池县,武胜县,邻水县,华蓥市";
var CountyCodes001019013="001019013001,001019013002,001019013003,001019013004,001019013005";
var CountyNames001019014="雨城区,名山县,荥经县,汉源县,石棉县,天全县,芦山县,宝兴县";
var CountyCodes001019014="001019014001,001019014002,001019014003,001019014004,001019014005,001019014006,001019014007,001019014008";
var CountyNames001019015="通川区,达　县,宣汉县,开江县,大竹县,渠　县,万源市";
var CountyCodes001019015="001019015001,001019015002,001019015003,001019015004,001019015005,001019015006,001019015007";
var CountyNames001019016="巴州区,通江县,南江县,平昌县";
var CountyCodes001019016="001019016001,001019016002,001019016003,001019016004";
var CountyNames001019017="东坡区,仁寿县,彭山县,洪雅县,丹棱县,青神县";
var CountyCodes001019017="001019017001,001019017002,001019017003,001019017004,001019017005,001019017006";
var CountyNames001019018="雁江区,安岳县,乐至县,简阳市";
var CountyCodes001019018="001019018001,001019018002,001019018003,001019018004";
var CountyNames001019019="汶川县,理　县,茂　县,松潘县,九寨沟县,金川县,小金县,黑水县,马尔康县,壤塘县,阿坝县,若尔盖县,红原县";
var CountyCodes001019019="001019019001,001019019002,001019019003,001019019004,001019019005,001019019006,001019019007,001019019008,001019019009,001019019010,001019019011,001019019012,001019019013";
var CountyNames001019020="康定县,泸定县,丹巴县,九龙县,雅江县,道孚县,炉霍县,甘孜县,新龙县,德格县,白玉县,石渠县,色达县,理塘县,巴塘县,乡城县,稻城县,得荣县";
var CountyCodes001019020="001019020001,001019020002,001019020003,001019020004,001019020005,001019020006,001019020007,001019020008,001019020009,001019020010,001019020011,001019020012,001019020013,001019020014,001019020015,001019020016,001019020017,001019020018";
var CountyNames001019021="西昌市,木里藏族自治县,盐源县,德昌县,会理县,会东县,宁南县,普格县,布拖县,金阳县,昭觉县,喜德县,冕宁县,越西县,甘洛县,美姑县,雷波县";
var CountyCodes001019021="001019021001,001019021002,001019021003,001019021004,001019021005,001019021006,001019021007,001019021008,001019021009,001019021010,001019021011,001019021012,001019021013,001019021014,001019021015,001019021016,001019021017";
var CountyNames001020001="城东区,城中 区,城西区,城北区,大通回族土族自治县,湟中县,湟源县";
var CountyCodes001020001="001020001001,001020001002,001020001003,001020001004,001020001005,001020001006,001020001007";
var CountyNames001020002="平安县,民和回族土族自治县,乐都县,互助土族自治县,化隆回族自治县,循化撒拉族自治县";
var CountyCodes001020002="001020002001,001020002002,001020002003,001020002004,001020002005,001020002006";
var CountyNames001020003="门源回族自治县,祁连县,海晏县,刚察县";
var CountyCodes001020003="001020003001,001020003002,001020003003,001020003004";
var CountyNames001020004="同仁县,尖扎县,泽库县,河南蒙古族自治县";
var CountyCodes001020004="001020004001,001020004002,001020004003,001020004004";
var CountyNames001020005="共和县,同德县,贵德县,兴海县,贵南县";
var CountyCodes001020005="001020005001,001020005002,001020005003,001020005004,001020005005";
var CountyNames001020006="玛沁县,班玛县,甘德县,达日县,久治县,玛多县";
var CountyCodes001020006="001020006001,001020006002,001020006003,001020006004,001020006005,001020006006";
var CountyNames001020007="玉树县,杂多县,称多县,治多县,囊谦县,曲麻莱县";
var CountyCodes001020007="001020007001,001020007002,001020007003,001020007004,001020007005,001020007006";
var CountyNames001020008="格尔木市,德令哈市,乌兰县,都兰县,天峻县";
var CountyCodes001020008="001020008001,001020008002,001020008003,001020008004,001020008005";
var CountyNames001021005="汉台区,南郑县,城固县,洋　县,西乡县,勉　县,宁强县,略阳县,镇巴县,留坝县,佛坪县";
var CountyCodes001021005="001021005001,001021005002,001021005003,001021005004,001021005005,001021005006,001021005007,001021005008,001021005009,001021005010,001021005011";
var CountyNames001021006="王益区,印台区,耀州区,宜君县";
var CountyCodes001021006="001021006001,001021006002,001021006003,001021006004";
var CountyNames001021007="临渭区,华　县,潼关县,大荔县,合阳县,澄城县,蒲城县,白水县,富平县,韩城市,华阴市";
var CountyCodes001021007="001021007001,001021007002,001021007003,001021007004,001021007005,001021007006,001021007007,001021007008,001021007009,001021007010,001021007011";
var CountyNames001021008="榆阳区,神木县,府谷县,横山县,靖边县,定边县,绥德县,米脂县,佳　县,吴堡县,清涧县,子洲县";
var CountyCodes001021008="001021008001,001021008002,001021008003,001021008004,001021008005,001021008006,001021008007,001021008008,001021008009,001021008010,001021008011,001021008012";
var CountyNames001021009="商州区,洛南县,丹凤县,商南县,山阳县,镇安县,柞水县";
var CountyCodes001021009="001021009001,001021009002,001021009003,001021009004,001021009005,001021009006,001021009007";
var CountyNames001021010="汉滨区,汉阴县,石泉县,宁陕县,紫阳县,岚皋县,平利县,镇坪县,旬阳县,白河县";
var CountyCodes001021010="001021010001,001021010002,001021010003,001021010004,001021010005,001021010006,001021010007,001021010008,001021010009,001021010010";
var CountyNames001022003="南关,宽城区,朝阳区,二道区,绿园区,双阳区,农安县,九台市,榆树市,德惠市";
var CountyCodes001022003="001022003001,001022003002,001022003003,001022003004,001022003005,001022003006,001022003007,001022003008,001022003009,001022003010";
var CountyNames001022004="龙山区,西安区,东丰县,东辽县";
var CountyCodes001022004="001022004001,001022004002,001022004003,001022004004";
var CountyNames001022005="铁西区,铁东区,梨树县,伊通满族自治县,公主岭市,双辽市";
var CountyCodes001022005="001022005001,001022005002,001022005003,001022005004,001022005005,001022005006";
var CountyNames001022006="东昌区,二道江区,通化县,辉南县,柳河县,梅河口市,集安市";
var CountyCodes001022006="001022006001,001022006002,001022006003,001022006004,001022006005,001022006006,001022006007";
var CountyNames001022007="宁江区,前郭尔罗斯蒙古族自治县,长岭县,乾安县,扶余县";
var CountyCodes001022007="001022007001,001022007002,001022007003,001022007004,001022007005";
var CountyNames001022008="延吉市,图们市,敦化市,珲春市,龙井市,和龙市,汪清县,安图县";
var CountyCodes001022008="001022008001,001022008002,001022008003,001022008004,001022008005,001022008006,001022008007,001022008008";
var CountyNames001022009="昌邑区,龙潭区,船营区,丰满区,永吉县,蛟河市,桦甸市,舒兰市,磐石市";
var CountyCodes001022009="001022009001,001022009002,001022009003,001022009004,001022009005,001022009006,001022009007,001022009008,001022009009";
var CountyNames001023002="大武口区,惠农区,平罗县";
var CountyCodes001023002="001023002001,001023002002,001023002003";
var CountyNames001023003="利通区,盐池县,同心县,青铜峡市";
var CountyCodes001023003="001023003001,001023003002,001023003003,001023003004";
var CountyNames001023004="沙坡头区,中宁县,海原县";
var CountyCodes001023004="001023004001,001023004002,001023004003";
var CountyNames001023005="兴庆区,西夏区,金凤区,永宁县,贺兰县,灵武市";
var CountyCodes001023005="001023005001,001023005002,001023005003,001023005004,001023005005,001023005006";
var CountyNames001026002="那曲县,嘉黎县,比如县,聂荣县,安多县,申扎县,索　县,班戈县,尼玛县,巴青县";
var CountyCodes001026002="001026002001,001026002002,001026002003,001026002004,001026002005,001026002006,001026002007,001026002008,001026002010,001026002009";
var CountyNames001026003="昌都县,江达县,贡觉县,类乌齐县,丁青县,察雅县,八宿县,左贡县,芒康县,洛隆县,边坝县";
var CountyCodes001026003="001026003001,001026003002,001026003003,001026003004,001026003005,001026003006,001026003007,001026003008,001026003009,001026003010,001026003011";
var CountyNames001026004="乃东县,扎囊县,贡嘎县,桑日县,琼结县,曲松县,措美县,洛扎县,加查县,隆子县,错那县,浪卡子县";
var CountyCodes001026004="001026004001,001026004002,001026004003,001026004004,001026004005,001026004006,001026004007,001026004008,001026004009,001026004010,001026004011,001026004012";