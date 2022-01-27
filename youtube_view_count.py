# This script can get the youtube video stats like view count,like count using the youtube API
import os

import google_auth_oauthlib.flow
import googleapiclient.discovery
import googleapiclient.errors
import pickle

scopes = ["https://www.googleapis.com/auth/youtube.readonly"]
client_secrets_file = "client_secret.json"
api_service_name = "youtube"
api_version = "v3"

def get_authenticated_service():
    if os.path.exists("CREDENTIALS_PICKLE_FILE"):
        with open("CREDENTIALS_PICKLE_FILE", 'rb') as f:
            credentials = pickle.load(f)
    else:
        flow = google_auth_oauthlib.flow.InstalledAppFlow.from_client_secrets_file(client_secrets_file, scopes)
        credentials = flow.run_console()
        with open("CREDENTIALS_PICKLE_FILE", 'wb') as f:
            pickle.dump(credentials, f)
    return googleapiclient.discovery.build(api_service_name, api_version, credentials=credentials)


def batch(iterable, n=1):
    l = len(iterable)
    for ndx in range(0, l, n):
        yield iterable[ndx:min(ndx + n, l)]


def main():
    # Disable OAuthlib's HTTPS verification when running locally.
#     # *DO NOT* leave this option enabled in production.
    os.environ["OAUTHLIB_INSECURE_TRANSPORT"] = "1"



    # Get credentials and create an API client
    flow = google_auth_oauthlib.flow.InstalledAppFlow.from_client_secrets_file(
        client_secrets_file, scopes)
    youtube = get_authenticated_service()

    li = ["rbiyXc6qLVw","5chWhLEyBOg","KXsLjQ6h_K4","U2DBsZhl2NI","iXz4y-QNRJE","C62teE4IiO4","yrCnNyJz64Y","VDEilW1AHYk","G0XP1-9_Kq0","rRC7ef1xyak","-gb7SbGaIUM","uUMf2JptRe4","rPze046drgo","okqPPT7dZ90","NhJyOHUo1bk","yhtP4AHMWYM","Tec9AtyE4vA","2-9p1anE1wM","lDibXS1FI-0","6BabxH2nQ8E","SZg9ZNNvDAo","2-3T84SzCF8","Z4PnyiWIhvg","IYt8_PiM3IM","rbgD-NmOAEA","Viy1WJz46cg","n0G4ZXqZkCM","PKhHfqo8HtI","ResiI2b-LSA","T_GGqRqH1hA","Z2E3GLWYOhQ","twZt2WTkomA","fTkgJPFeUkU","g19wZt_L5q8","vrGDFNdCb5w","TdaslGTQ0U0","EJ6pINWs5CY","Q5w9hcG37as","Du3twljmU-E","ehgXHa-HINg","a2YDEaCG2NE","T6MxdFUx2y0","FgRI7Hxrw7c","pq0Vu9v2fhE","5U7wrOKiuQQ","uga8LsNMIOY","au-l7I1ThVE","bDv46e5DSlg","gp1Q-tCB5-c","IBaSFsReFwQ","pqS14vjXCFM","RLrfGilAwwk","GgljOmyHjno","3vcyiw805Wg","1He2W-3FiMw","F8G5Z61FkSM","pGM_RXYHXRo","uGM7Gk6j57E","eVdlvYJ1NrE","wG9bIdbyPjw","ahRwAOTfI64","hos9-4yVtXI","WaBnR1Hf79o","KuaESY_tzJ0","MM-HlX8L4Vw","HFYM1wrBF4w","JAcBTX8ZS64","1NNDFf6ZgGw","hwFbo4mCnO4","yE3fuMyZRnM","eDPPRC8q74Q","hpqhWbBhkek","m_m_UVJcqaA","47SB7cu2ovc","QUTURWJUq04","2TDcgVI1cjM","WQ2B5KUrb7Y","ZSOn4K_wWk4","5UCGe6CJco8","aX-zsq9UJpc","N2uVc_K-4gA","ftykLCdt-00","k8Wu5FoIaL0","NQcYO8fHW3M","PWNx3k55jDI","GBPEbphKkCs","g4UDMmBXID0","eeWptsKFpzw","FXkajmM51qM","82DOpa3WHgo","Vus7vORXoxc","L1Ojb5O3ZnI","9PW_4FxH3qI","zPKcwL6t2ho","hDCUwfHApBU","u-KqjS2LD8A","XRoSpi9nMD4","0Y2nY8OlynA","jbDNrid7wDs","Yoz7G3dF3UM","IsH-aQtBGcw","iqzNYgEZMj8","nM-pWcIN_Yg","Yv0Bid1Epz8","uRNQrn_UvZ8","khsE_3VTP8M","_fDSmwiEwtw","NLS6OVAqyZY","vF_XbnzMHsk","0MRiVK6Qeac","Kk6gDslmvac","l_DS8yU83AM","Hh_fDLIHP2Q","vX8H-Wy3URg","8y8YsqERORc","UUIMAy0gYG0","EnVgK_dnntc","vZx-PUqei68","61tJzPjPkiM","I3xkPnuWwek","aILJtDAblcw","KiWzBLbQrcg","M1q_BSM8SDo","oc3NNITH574","wjJJY1dZWhY","tS1w6PCHODI","oLmhl19gTVQ","B9d__-ECVfI","2EbL7dTaGMo","RI4HJEQkPoQ","DlkQOyjoCcc","oVbhWNQ0YhI","R8dEjInsT3I","QscVsRMoFaM","RijIiiGzdhA","3VIvQIhwfTo","yLJvx8Li7eA","sJBXvXSf_ow","IKgBi77DqiI","hrMPCr5huA8","il_QAfMhbsU","jMPYH_UJjXM","U4RmPvNw298","dGy2a6akCYo","ImWKEIbrCTI","mGeS9bNtdqs","ZeXIYE6zCIg","i36Bfi9I9Ng","WKShvdCd_fw","h004V68_tPU","D2lqaF7kqOA","ZUAJb1u_J9Q","X70iw9ODprI","GjJnNrh0AJM","mb-3tusF2DY","IdW9E9W1Wc4","L3mVyKrFqGI","9Qlf2MxQ6M4","mGRhBOgR8T8","mGRhBOgR8T8","3gtjNBXSTGs","OEvn5F7vns4","RGRanCGy09U","ZkGqVss5pOQ","UIbwhVvMb4g","erlNsorcP-0","YXNn2WZyM5g","En1QiC3hFlo","JKBJFB_vsQc","iLRyzByMtbY","s_IsaVKp0lI","g7cPIQJWKho","bQfZyZhCnWU","bQfZyZhCnWU","U25mg4WyqC4","TFxhgabNzcY","dj8CLc2KsD0","nYcjzrZ4hsQ","gGl-7XFQUAQ","dm_RykobuTI","ubWklVCY-FI","1EiEzIRL5h8","wfUDaYFuxKk","HDftoKegq-o","IHgeM92Ne2k","ce4w9TI4fdg","Y4M0awOJQtU","IY8ImY5EpFs","11WHAW6XlNs","y_JRtb--Kqo","XdLxe90mFYM","UYPQO-Pxyc8","tQ_UB1EkdtM","h8PfdCIuyyo","7ytNGfUNsU8","Lh-D2gLnt-U","R6a_FqLWV_A","A3zyRde1rZU","0vOcGiFzh7Q","V3FPf6XrzhQ","V3FPf6XrzhQ","Hpbe7X9SFNk","Hpbe7X9SFNk","4qcPsiGadF4","EV3Bo18WzpE","XjWqHoRFp-8","Raze8GvMu44","Raze8GvMu44","1sxqXhtJxzY","qcPNAcLObys","kz8nYm2lwZ0","nYjh-9-Ewcg","mT3lyi3KNp8","FgAD075IOq0","ocecCWkr9z8","vLximA5qqQI","S8lN5DmBJ3U","w-bt1utMHwQ","6OfP93UahUY","9h0JZuol6cI","9h0JZuol6cI","dTxhANcGBdk","mndAPYUvEhM","d7A32A3q6AY","v6LyfIauYs0","0eajsWl5XKw","0eajsWl5XKw"]
    
    for x in batch(li, 50):
        idList=",".join(x)
        #print(idList)
    
        request = youtube.videos().list(
            part="statistics",
            id=idList
        )
        response = request.execute()

        #print(response)

        items = response["items"]
        for item in items:
            stat=item["statistics"]
            print("{},{},{}".format(item["id"],stat["viewCount"],stat["likeCount"]))

if __name__ == "__main__":
    main()
