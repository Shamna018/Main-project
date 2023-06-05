#
# from gtts import gTTS
#
# import os
#
# mytext = 'Thank you'
#
# language = 'en'
# myobj = gTTS(text=mytext, lang=language, slow=False)
#
# myobj.save("welcome.mp3")
#
#
# os.system("welcome.mp3")
import pyttsx3
engine = pyttsx3.init()
engine.say("I will speak this text")
voices = engine.getProperty('voices')       #getting details of current voice
engine.setProperty('voice', voices[1].id)
engine.runAndWait()