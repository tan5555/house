package com.k9509.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
  
  
public class HttpClientUtil {  
    private RequestConfig requestConfig = RequestConfig.custom()  
            .setSocketTimeout(15000)  
            .setConnectTimeout(15000)  
            .setConnectionRequestTimeout(15000)  
            .build();  
      
    private static HttpClientUtil instance = null;  
    private HttpClientUtil(){}  
    public static HttpClientUtil getInstance(){  
        if (instance == null) {  
            instance = new HttpClientUtil();  
        }  
        return instance;  
    }  
      
    /** 
     * ���� post���� 
     * @param httpUrl ��ַ 
     */  
    public String sendHttpPost(String httpUrl) {  
        HttpPost httpPost = new HttpPost(httpUrl);// ����httpPost    
        return sendHttpPost(httpPost,"utf-8");  
    }  
      
      
    /** 
     * ���� post���� 
     * @param httpUrl ��ַ 
     * @param maps ���� 
     *  @param type �ַ������ʽ 
     */  
    public String sendHttpPost(String httpUrl, Map<String, String> maps,String type) {  
        HttpPost httpPost = new HttpPost(httpUrl);// ����httpPost    
        // ������������    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        for (String key : maps.keySet()) {  
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
        }  
        try {  
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, type));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sendHttpPost(httpPost,type);  
    }  
      
    /** 
     * ����Post���� 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpPost(HttpPost httpPost,String reponseType) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // ����Ĭ�ϵ�httpClientʵ��.  
            httpClient = HttpClients.createDefault();  
            httpPost.setConfig(requestConfig);  
            // ִ������  
            response = httpClient.execute(httpPost);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, reponseType);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // �ر�����,�ͷ���Դ  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
  
    /** 
     * ���� get���� 
     * @param httpUrl 
     */  
    public String sendHttpGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);// ����get����  
        return sendHttpGet(httpGet);  
    }  
      
    /** 
     * ���� get����Https 
     * @param httpUrl 
     */  
    public String sendHttpsGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);// ����get����  
        return sendHttpsGet(httpGet);  
    }  
    
    /**
     * @Title: sendMsgUtf8
     * @Description: TODO(����utf8)
     * @param: @param Uid
     * @param: @param Key
     * @param: @param content
     * @param: @param mobiles
     * @param: @return     
     * @return: int     
     * @author:  ly
     * @date: 2017-3-22 ����5:58:07
     * @throws
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int sendMsgUtf8(String Uid,String Key,String content,String mobiles){
    	Map maps = new HashMap();
		maps.put("Uid", Uid);
		maps.put("Key", Key);
		maps.put("smsMob", mobiles);
		maps.put("smsText", content);
		String result = sendHttpPost("http://utf8.sms.webchinese.cn", maps, "utf-8");
		return Integer.parseInt(result);
    }
    
    /**
     * @Title: sendMsgUtf8
     * @Description: TODO(����utf8)
     * @param: @param Uid
     * @param: @param Key
     * @param: @param content
     * @param: @param mobiles
     * @param: @return     
     * @date: 2017-3-22 ����5:58:07
     * @throws
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int sendMsgGbk(String Uid,String Key,String content,String mobiles){
    	Map maps = new HashMap();
		maps.put("Uid", Uid);
		maps.put("Key", Key);
		maps.put("smsMob", mobiles);
		maps.put("smsText", content);
		String result = sendHttpPost("http://gbk.sms.webchinese.cn", maps, "gbk");
		return Integer.parseInt(result);
    }
      
    /** 
     * ����Get���� 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpGet(HttpGet httpGet) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // ����Ĭ�ϵ�httpClientʵ��.  
            httpClient = HttpClients.createDefault();  
            httpGet.setConfig(requestConfig);  
            // ִ������  
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // �ر�����,�ͷ���Դ  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
      
    /** 
     * ����Get����Https 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpsGet(HttpGet httpGet) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // ����Ĭ�ϵ�httpClientʵ��.  
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));  
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);  
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();  
            httpGet.setConfig(requestConfig);  
            // ִ������  
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // �ر�����,�ͷ���Դ  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
    
    /**
	 * @Title: getErrorMsg
	 * @Description: TODO(�����쳣ԭ��)
	 * @param: @param errorCode
	 */
	public String getErrorMsg(int errorCode){
		if(errorCode==-1){
			return "û�и��û��˻�";
		}else if(errorCode==-2){
			return "�ӿ���Կ����ȷ";
		}else if(errorCode==-3){
			return "������������";
		}else if(errorCode==-4){
			return "�ֻ��Ÿ�ʽ����ȷ";
		}else if(errorCode==-21){
			return "MD5�ӿ���Կ���ܲ���ȷ";
		}else if(errorCode==-11){
			return "���û�������";
		}else if(errorCode==-14){
			return "�������ݳ��ַǷ��ַ�";
		}else if(errorCode==-41){
			return "�ֻ�����Ϊ��";
		}else if(errorCode==-42){
			return "��������Ϊ��";
		}else if(errorCode==-51){
			return "����ǩ����ʽ����ȷ";
		}else if(errorCode==-6){
			return "IP����";
		}else{
			return "δ֪������:"+errorCode;
		}
	}
}  