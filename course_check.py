import requests
import urllib.request
import time
from bs4 import BeautifulSoup
import pandas as pd

clist=["17573","18885","29333","22407","17170","27873","22340","22352"]

template = "https://webapp4.asu.edu/catalog/coursedetails?r="

for course in clist:
    url = template+course
    
    response = requests.get(url)
    #print(response.text)
    soup = BeautifulSoup(response.text,"html.parser")
    
    #table = soup.find("#reserved-tbl")
    name = soup.find("h2").text
    print(name)
    table = soup.find("table", {"id": "reserved-tbl"})
    df = pd.read_html(str(table))[0]
    #print(df.shape)
    #print(df.columns)
    df.columns = ["Reserved Groups","Reserved avl.","Enrolled","Total Reserved","Until"]
    df = df.drop(columns=['Enrolled','Total Reserved', 'Until'])
    df["Reserved Groups"] = df["Reserved Groups"].str[-50:]
    print(" ",)
    print(df)
    #print(table)
    
    #row= table.find("td",{"class":"total"}).text
    #print(" ",course," ",row
    print("=============================================================\n\n")
