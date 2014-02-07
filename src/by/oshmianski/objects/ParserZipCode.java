package by.oshmianski.objects;

import by.oshmianski.ui.edt.UIProcessor;
import by.oshmianski.utils.MyLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vintselovich on 06.02.14.
 */
public class ParserZipCode {
    private String folderPath;
    private UIProcessor ui;

    public ParserZipCode(String folderPath, UIProcessor ui) {
        this.folderPath = folderPath;
        this.ui = ui;
    }

    public void process() {
        File input = null;
        PostOffice postOffice = null;
        IndexItem indexItem = null;

        Document doc = null;
        Element zip_code = null;
        Element indexStruct = null;
        Elements cities = null;
        CityItem cityItem = null;
        Map<String, String> keys = new HashMap<String, String>();

        try {
            File folder = new File(folderPath);

            File[] listOfFiles = folder.listFiles();

            ui.setProgressMaximum(listOfFiles.length);
            ui.setProgressLabelText("0 [0]");
            ui.setProgressValue(0);

            MyLog.add2Log("===========\nЗагрузка данных", false);

            int indexSpace;
            int counter = 0;
            int counter2 = 0;
            String cityStr;
            String region;
            String district;
            String unit;
            String cityType;
            String cityTitle;
            String key;
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    input = new File(listOfFiles[i].getAbsolutePath());

                    doc = Jsoup.parse(input, "windows-1251", "");

                    zip_code = doc.select("input[name=zip_code]").first();

                    if (zip_code == null) {
                        MyLog.add2Log(listOfFiles[i].getName() + ": нет zip_code!", false);
                    } else {
                        indexStruct = doc.select("div.context div:eq(2)").first();

                        cities = doc.select("table.tblcity td:has(a)");

                        postOffice = getIndexItem(zip_code.attr("value"), indexStruct);

                        region = WordUtils.capitalizeFully(indexStruct.textNodes().get(2).text().toLowerCase().replace("область", "").trim(), '-', ' ', '.');
                        district = WordUtils.capitalizeFully(indexStruct.textNodes().get(3).text().toLowerCase().replace("район", "").trim(), '-', ' ', '.');
                        unit = WordUtils.capitalizeFully(indexStruct.textNodes().get(4).text().toLowerCase().replace("поселковый совет", "").trim(), '-', ' ', '.');

                        if (cities.size() > 0) {
                            counter2++;
                        } else {
                            MyLog.add2Log(listOfFiles[i].getName() + ": нет нас. пунктов!", false);
                        }

                        for (Element city : cities) {
                            cityStr = city.text();
                            indexSpace = cityStr.indexOf(' ');

                            cityType = StringUtils.left(cityStr, indexSpace).trim();
                            cityTitle = WordUtils.capitalizeFully(StringUtils.substring(cityStr, indexSpace).trim(), '-', ' ', '.');

                            key = region + district + unit + cityType + cityTitle;

                            if (!keys.containsKey(key)) {
                                cityItem = new CityItem(
                                        region,
                                        district,
                                        unit,
                                        cityType,
                                        cityTitle
                                );


                                indexItem = new IndexItem(zip_code.attr("value"), cityItem, listOfFiles[i].getName(), postOffice);

                                ui.appendIndex(indexItem);

                                keys.put(key, "");
                            }
                        }
                    }

                    doc = null;
                    zip_code = null;
                    indexStruct = null;
                    cities = null;
                    postOffice = null;
                    indexItem = null;
                    cityItem = null;
                }

                counter++;

                ui.setProgressValue(counter);
                ui.setProgressLabelText(counter + " [" + counter2 + "]");
            }

            keys.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private PostOffice getIndexItem(String zip_code, Element indexStruct) {
        PostOffice postOffice;

        List<TextNode> nodes = indexStruct.textNodes();

        postOffice = new PostOffice();
        postOffice.setIndex(zip_code);

        postOffice.setTitle(nodes.get(1).text());
        postOffice.setRegion(nodes.get(2).text());
        postOffice.setDistrict(nodes.get(3).text());
        postOffice.setUnit(nodes.get(4).text());
        postOffice.setChief(nodes.get(5).text());
        postOffice.setChiefDeputy(nodes.get(6).text());
        postOffice.setPhone(nodes.get(7).text());
        postOffice.setFax(nodes.get(8).text());
        postOffice.setCityCode(nodes.get(9).text());

        postOffice.setCityCodeInternational(nodes.get(11).text());
        postOffice.setPhoneDelivery(nodes.get(12).text());
        postOffice.setAddress(nodes.get(13).text());

        return postOffice;
    }
}
