# import the opencv library
def detect_face_post():
    import cv2
    import face_recognition

    from DBConnection import Db
    db = Db()

    qry = "SELECT * FROM `staffmanage_app_staff`"
    res = db.select(qry)

    # qry1 = "SELECT * FROM `man missing`"
    # res1 = db.select(qry1)

    knownimage = []
    knownids = []
    knowntype = []

    for i in res:
        s = i['photo']
        s = s.replace("/", "\\")
        pth = "C:\\Users\\FASAL\\PycharmProjects\\staffmanage" + s


        picture_of_me = face_recognition.load_image_file(pth)
        try:
            my_face_encoding = face_recognition.face_encodings(picture_of_me)[0]
            # totface = len(my_face_encoding)
            # print(totface, "newtot n res")

            knownimage.append(my_face_encoding)
            knownids.append(i['id'])
            # knowntype.append('Criminal')
            
        except Exception as e:
            print("None detected: ", e)

    # for i in res1:
    #     s = i['photo']
    #     s = s.replace("/", "\\")
    #     pth = "D:\\autothief_detection" + s
    #
    #     picture_of_me = face_recognition.load_image_file(pth)
    #     # print(pth)
    #     try:
    #         my_face_encoding = face_recognition.face_encodings(picture_of_me)[0]
    #         # print(my_face_encoding)
    #         knownimage.append(my_face_encoding)
    #         knownids.append(i['man_missing_id'])
    #         knowntype.append('Missing')
    #     except Exception as e:
    #         print("None detected: ", e)


    vid = cv2.VideoCapture(0)
    # vid = cv2.VideoCapture(r"D:\autothief_detection\static\Criminals\WhatsApp Video 2023-03-23 at 10.42.56.mp4")
    # vid = cv2.VideoCapture("http://192.168.29.53:4000/video")

    while (True):

        ret, frame = vid.read()
        img=cv2.imread(r"C:\Users\FASAL\PycharmProjects\untitled\photos\20230331123406.jpg")

        from datetime import datetime
        ttt = datetime.now().strftime("%Y%m%d%H%M%S") + ".jpg"
        # dt = datetime.now().strftime("%H")
        # print(dt)
        try:
            cv2.imwrite(r"C:\Users\FASAL\PycharmProjects\untitled\photos\\" + ttt, frame)
        except Exception as e:
            print(e)




        picture_of_others = face_recognition.load_image_file(
            "C:\\Users\\FASAL\\PycharmProjects\\untitled\\photos\\" + ttt)
        # print(pth)
        others_face_encoding = face_recognition.face_encodings(picture_of_others)

        totface = len(others_face_encoding)

        from datetime import datetime

        # curh = float(str(datetime.now().time().hour) + "." + str(datetime.now().time().minute))

        # print(curh, "hgfhhgfgfghfghfgh")
        # print(totface, "tot")

        for i in range(0, totface):
            res3 = face_recognition.compare_faces(knownimage, others_face_encoding[i], tolerance=0.5)
            # print(res3)

            # print(knowntype[i])
            # print(knownids[i])
            for j in range(len(res3)):
                if res3[j] == True:
                    print(knownids[j])
                    # print(knownids[i], knowntype[i], " Known")
                    # print(knownids[j], " Known")
                    # for k in knownids:
                    path = '/C:/Users/FASAL/PycharmProjects/untitled/photos/'+ttt
                    qry = "SELECT * FROM `staffmanage_app_attendance` WHERE DATE=CURDATE() AND staff_id='"+str(knownids[j])+"'"
                    dt = datetime.now().strftime("%H")
                    mt = datetime.now().strftime("%M")
                    mt=int(mt)
                    if dt < '09':
                        print("hit1")
                        qry1="SELECT * FROM`staffmanage_app_attendance` WHERE staff_id='"+str(knownids[j])+"' AND DATE=CURDATE()"
                        res1=db.selectOne(qry1)
                        if res1 is None:
                            print("hit2")

                            qry="INSERT INTO `staffmanage_app_attendance`(DATE,entry_time,exit_time,entry_status,exit_status,staff_id)VALUES(CURDATE(),CURTIME(),'pending','Ontime','pending','"+str(knownids[j])+"')"
                            db.insert(qry)
                            import pyttsx3
                            engine = pyttsx3.init()
                            engine.say("Face recognised successfully")
                            voices = engine.getProperty('voices')  # getting details of current voice
                            engine.runAndWait()
                        else:
                            print("hit3")
                            import pyttsx3
                            engine = pyttsx3.init()
                            engine.say("Already Checked in")
                            voices = engine.getProperty('voices')  # getting details of current voice
                            engine.runAndWait()

                            # qry = "UPDATE `staffmanage_app_attendance` set entry_status='ontime',entry_time=CURTIME() where staff_id='"+str(knownids[j])+"' and date=curdate()"
                            # db.update(qry)

                    elif dt == '09' :
                        print("hit4")

                        if mt ==0:
                            print("hit5")

                            print('present')
                            qry1="SELECT * FROM`staffmanage_app_attendance` WHERE staff_id='"+str(knownids[j])+"' AND DATE=CURDATE()"
                            res1=db.selectOne(qry1)
                            if res1 is None:
                                print("hit6")

                                qry="INSERT INTO `staffmanage_app_attendance`(DATE,entry_time,exit_time,entry_status,exit_status,staff_id)VALUES(CURDATE(),CURTIME(),'pending','Ontime','pending','"+str(knownids[j])+"')"
                                db.insert(qry)
                                import pyttsx3
                                engine = pyttsx3.init()
                                engine.say("Face recognised successfully")
                                voices = engine.getProperty('voices')  # getting details of current voice
                                engine.setProperty('voice', voices[1].id)
                                engine.runAndWait()
                            else:
                                print("hit7")

                                # qry = "UPDATE `staffmanage_app_attendance` set entry_status='Ontime',entry_time=CURTIME() where staff_id='"+str(knownids[j])+"' and date=curdate()"
                                # db.update(qry)
                                import pyttsx3
                                engine = pyttsx3.init()
                                engine.say("Already checked in")
                                voices = engine.getProperty('voices')  # getting details of current voice
                                engine.setProperty('voice', voices[1].id)
                                engine.runAndWait()

                        else:
                            print("hit8")

                            qry1 = "SELECT * FROM`staffmanage_app_attendance` WHERE staff_id='" + str(
                                knownids[j]) + "' AND DATE=CURDATE()"
                            res1 = db.selectOne(qry1)
                            if res1 is None:
                                print("hit9")

                                qry = "INSERT INTO `staffmanage_app_attendance`(DATE,entry_time,exit_time,entry_status,exit_status,staff_id)VALUES(CURDATE(),CURTIME(),'pending','Late','pending','" + str(
                                    knownids[j]) + "')"
                                db.insert(qry)
                                import pyttsx3
                                engine = pyttsx3.init()
                                engine.say("Face recognised successfully")
                                voices = engine.getProperty('voices')  # getting details of current voice
                                # engine.setProperty('voice', voices[1].id)
                                engine.runAndWait()
                            else:
                                print("hit10")

                                # qry = "UPDATE `staffmanage_app_attendance` set entry_status='Late',entry_time=CURTIME() where staff_id='" + str(
                                #     knownids[j]) + "' and date=curdate()"
                                # db.update(qry)
                                import pyttsx3
                                engine = pyttsx3.init()
                                engine.say("Already checked in")
                                voices = engine.getProperty('voices')  # getting details of current voice
                                # engine.setProperty('voice', voices[1].id)
                                engine.runAndWait()

                    elif dt < '17':
                        print("hit11")

                        qry1="SELECT * FROM`staffmanage_app_attendance` WHERE staff_id='"+str(knownids[j])+"' AND DATE=CURDATE()"
                        res1=db.selectOne(qry1)

                        if res1 is None:
                            print("hit12")

                            qry = "INSERT INTO `staffmanage_app_attendance`(DATE,entry_time,exit_time,entry_status,exit_status,staff_id)VALUES(CURDATE(),CURTIME(),CURTIME(),'Late','Early','" + str(
                                knownids[j]) + "')"
                            db.insert(qry)

                            import pyttsx3
                            engine = pyttsx3.init()
                            engine.say("Face recognised successfully")
                            voices = engine.getProperty('voices')  # getting details of current voice
                            engine.setProperty('voice', voices[1].id)
                            engine.runAndWait()
                        else:
                            print("hit13")
                            qry1 = "SELECT * FROM`staffmanage_app_attendance` WHERE staff_id='" + str(
                                knownids[j]) + "' AND DATE=CURDATE() and exit_status='pending' "
                            res1 = db.selectOne(qry1)
                            print(qry1)
                            print(res1)
                            if res1 is not None:
                                qry = "UPDATE `staffmanage_app_attendance` set exit_status='Early', exit_time=CURTIME() where staff_id='" + str(
                                    knownids[j]) + "' and date=curdate()"
                                db.update(qry)
                                print(qry)
                                import pyttsx3
                                engine = pyttsx3.init()
                                engine.say("Face recognised successfully")
                                voices = engine.getProperty('voices')  # getting details of current voice
                                engine.runAndWait()
                            else:
                                import pyttsx3
                                engine = pyttsx3.init()
                                engine.say("Already checked out")
                                voices = engine.getProperty('voices')  # getting details of current voice
                                engine.runAndWait()

                    elif dt > '17':
                        print("hit14")

                        qry1="SELECT * FROM`staffmanage_app_attenda" \
                             "nce` WHERE staff_id='"+str(knownids[j])+"' AND DATE=CURDATE() and exit_status='pending'"
                        res1=db.selectOne(qry1)
                        if res1 is not None:
                            qry = "UPDATE `staffmanage_app_attendance` set exit_status='Late',exit_time=CURTIME() where staff_id='"+str(knownids[j])+"' and date=curdate()"
                            db.update(qry)
                            import pyttsx3
                            engine = pyttsx3.init()
                            engine.say("Face recognised successfully")
                            voices = engine.getProperty('voices')  # getting details of current voice
                            engine.setProperty('voice', voices[1].id)
                            engine.runAndWait()
                        else:
                            print("al1")
                            import pyttsx3
                            engine = pyttsx3.init()
                            engine.say("Already checked out")
                            voices = engine.getProperty('voices')  # getting details of current voice
                            engine.runAndWait()

                    elif dt == '17':
                        print("hit15")

                        if mt ==0:
                            print("hit16")
                            qry1="SELECT * FROM `staffmanage_app_attendance` WHERE staff_id='"+str(knownids[j])+"' AND DATE=CURDATE() "
                            res1=db.selectOne(qry1)
                            if res1 is None:
                                print("hit17")
                                qry="INSERT INTO `staffmanage_app_attendance`(DATE,entry_time,exit_time,entry_status,exit_status,staff_id)VALUES(CURDATE(),CURTIME(),curtime(),'Late','Ontime','"+str(knownids[j])+"')"
                                db.insert(qry)
                                import pyttsx3
                                engine = pyttsx3.init()
                                engine.say("Face recognised successfully")
                                voices = engine.getProperty('voices')  # getting details of current voice
                                # engine.setProperty('voice', voices[1].id)
                                engine.runAndWait()
                            else:
                                print("hit18.1")
                                qry1="SELECT * FROM `staffmanage_app_attendance` WHERE staff_id='"+str(knownids[j])+"' AND DATE=CURDATE() and  exit_status='pending'"
                                res1=db.selectOne(qry1)
                                if res1 is not None:
                                    qry = "UPDATE `staffmanage_app_attendance` set exit_status='Ontime',exit_time=CURTIME() where staff_id='" + str(
                                        knownids[j]) + "' and date=curdate()"
                                    db.update(qry)
                                # import pyttsx3
                                # engine = pyttsx3.init()
                                # engine.say("Face recognised successfully")
                                # voices = engine.getProperty('voices')  # getting details of current voice
                                # engine.setProperty('voice', voices[1].id)
                                # engine.runAndWait()
                        else:
                            print("hit18.2")
                            qry1 = "SELECT * FROM `staffmanage_app_attendance` WHERE staff_id='" + str(
                                knownids[j]) + "' AND DATE=CURDATE() "
                            res1 = db.selectOne(qry1)
                            if res1 is None:
                                print("hit17")
                                qry = "INSERT INTO `staffmanage_app_attendance`(DATE,entry_time,exit_time,entry_status,exit_status,staff_id)VALUES(CURDATE(),CURTIME(),curtime(),'Late','Late','" + str(
                                    knownids[j]) + "')"
                                db.insert(qry)
                                import pyttsx3
                                engine = pyttsx3.init()
                                engine.say("Face recognised successfully")
                                voices = engine.getProperty('voices')  # getting details of current voice
                                # engine.setProperty('voice', voices[1].id)
                                engine.runAndWait()
                            else:
                                qry1 = "SELECT * FROM `staffmanage_app_attendance` WHERE staff_id='" + str(
                                    knownids[j]) + "' AND DATE=CURDATE() and  exit_status='pending'"
                                res1 = db.selectOne(qry1)
                                print(res1)
                                print(qry1)
                                if res1 is not None:
                                    qry = "UPDATE `staffmanage_app_attendance` set exit_status='Late',exit_time=CURTIME() where staff_id='" + str(
                                        knownids[j]) + "' and date=curdate()"
                                    db.update(qry)
                                    import pyttsx3
                                    engine = pyttsx3.init()
                                    engine.say("Face recognised successfully")
                                    voices = engine.getProperty('voices')  # getting details of current voice
                                    # engine.setProperty('voice', voices[1].id)
                                    engine.runAndWait()

                                else:
                                    print("a2")
                                    import pyttsx3
                                    engine = pyttsx3.init()
                                    engine.say("Already checked out")
                                    voices = engine.getProperty('voices')  # getting details of current voice
                                    # engine.setProperty('voice', voices[1].id)
                                    engine.runAndWait()


                    else:
                        print("hit19")

                        qry1 = "SELECT * FROM`staffmanage_app_attendance` WHERE staff_id='" + str(
                            knownids[j]) + "' AND DATE=CURDATE()"
                        res1=db.selectOne(qry1)
                        if res1 is None:
                            print("hit20")

                            qry = "INSERT INTO `staffmanage_app_attendance`(DATE,entry_time,exit_time,entry_status,exit_status,staff_id)VALUES(CURDATE(),CURTIME(),'pending'','Late','pending','" +str(knownids[j]) + "')"
                            db.insert(qry)
                            import pyttsx3
                            engine = pyttsx3.init()
                            engine.say("Face recognised successfully")
                            voices = engine.getProperty('voices')  # getting details of current voice
                            engine.setProperty('voice', voices[1].id)
                            engine.runAndWait()
                        else:
                            print("hit21")

                            qry = "UPDATE `staffmanage_app_attendance` set entry_status='ontime',entry_time=CURTIME() where staff_id='" +str(knownids[j]) + "' and date=curdate()"
                            db.update(qry)
                            # import pyttsx3
                            # engine = pyttsx3.init()
                            # engine.say("Face recognised successfully")
                            # voices = engine.getProperty('voices')  # getting details of current voice
                            # engine.setProperty('voice', voices[1].id)
                            # engine.runAndWait()

                    # qrycheck = "select * from detection_details where date="
                    # if knowntype[i]=='Criminal':
                    #     print()
                        # qry = "insert into detection_details(`criminal_id`,`date`,`img`,`time`,`ulid`,`type`) values('"+str(knownids[j])+"',curdate(),'"+path+"',curtime(),'0','Criminal')"
                        # db.insert(qry)
                    # else:
                        # qry = "insert into detection_details(`misid`,`date`,`img`,`time`,`ulid`,`type`) values('" + str(
                        #     knownids[j]) + "',curdate(),'" + path + "',curtime(),'0','Missing')"
                        # db.insert(qry)

                # import time
                # time.sleep(60)

detect_face_post()