import os
import time

"""
This script uses android adb to simulate taps on the screen
I use this to farm soul eggs in the game Egg Inc.
"""

#initial load
os.system("adb shell input swipe 581 2256 581 2256 8000")

#sustain at max level
for i in range(0,100):
    print("Time: ",time.strftime("%H:%M:%S", time.localtime()))
    os.system("adb shell input swipe 581 2256 581 2256 4000")
    time.sleep(3)
    
    
print("ho gaya")
