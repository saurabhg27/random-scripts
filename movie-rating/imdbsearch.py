import requests

"""
Reads text file line by line
Hits OMDB api to get IMDB rating
write to another file

list.txt created by Ctrl+C on youtube,Ctrl+V and some notepad++ regex to get it to format
"""

file1 = open("list.txt", 'r')
Lines = file1.readlines()
list=[]
exceptlist=[]
count = 0
apichabi="foo"
for line in Lines:
    count += 1
    # print("Line{}: {}".format(count, line.strip()))
    res = requests.get("https://www.omdbapi.com/?t={}&apikey={}".format(line,apichabi))
    res=res.json()
    try:
        str=res["imdbRating"],"|",res["Title"],"|",res["Year"],"|",res["Genre"],"|",res["imdbRating"]
        print(str)
        list.append(str)
    except:
        exceptlist.append(line)
        print("Excep: ",line)
    
    

file1.close()
file2=open("results.txt",'w')
for element in list:
    file2.write(element + "\n")
file2.close()

print("ho gaya sab")
