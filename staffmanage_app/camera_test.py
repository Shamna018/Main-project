# import the opencv library
from django.core.files.storage import FileSystemStorage

from staffmanage_app.models import staff


def detect_face_post():
    import cv2
    import face_recognition

    # from DBConnection import Db
    db = Db()

    qry = "SELECT * FROM `criminal`"
    res = staff.objects.all

    # qry1 = "SELECT * FROM `man missing`"
    # res1 = db.select(qry1)

    knownimage = []
    knownids = []
    knowntype = []

    for i in res:
        s = i['photo']
        s = s.replace("/", "\\")
        pth = "D:\\autothief_detection" + s
        fs = FileSystemStorage()
        # fn = fs.save(date, photo)

        picture_of_me = face_recognition.load_image_file(pth)
        try:
            my_face_encoding = face_recognition.face_encodings(picture_of_me)[0]
            # totface = len(my_face_encoding)
            # print(totface, "newtot n res")

            knownimage.append(my_face_encoding)
            knownids.append(i['id'])
            knowntype.append('Criminal')
            
        except Exception as e:
            print("None detected: ", e)

    for i in res1:
        s = i['photo']
        s = s.replace("/", "\\")
        pth = "D:\\autothief_detection" + s

        picture_of_me = face_recognition.load_image_file(pth)
        # print(pth)
        try:
            my_face_encoding = face_recognition.face_encodings(picture_of_me)[0]
            # print(my_face_encoding)
            knownimage.append(my_face_encoding)
            knownids.append(i['man_missing_id'])
            knowntype.append('Missing')
        except Exception as e:
            print("None detected: ", e)


    # vid = cv2.VideoCapture()
    # vid = cv2.VideoCapture(r"D:\autothief_detection\static\Criminals\WhatsApp Video 2023-03-23 at 10.42.56.mp4")
    vid = cv2.VideoCapture("http://192.168.29.53:4000/video")

    while (True):

        ret, frame = vid.read()

        from datetime import datetime
        ttt = datetime.now().strftime("%Y%m%d%H%M%S") + ".jpg"
        cv2.imwrite(r"D:\autothief_detection\static\FD\Face_recognition\\" + ttt, frame)


        picture_of_others = face_recognition.load_image_file(
            "D:\\autothief_detection\\static\\FD\Face_recognition\\" + ttt)
        # print(pth)
        others_face_encoding = face_recognition.face_encodings(picture_of_others)

        totface = len(others_face_encoding)

        from datetime import datetime

        # curh = float(str(datetime.now().time().hour) + "." + str(datetime.now().time().minute))

        # print(curh, "hgfhhgfgfghfghfgh")
        # print(totface, "tot")

        for i in range(0, totface):
            res3 = face_recognition.compare_faces(knownimage, others_face_encoding[i], tolerance=0.45)
            # print(res3)

            # print(knowntype[i])
            # print(knownids[i])
            for j in res3:
                if j == True:
                    # print(knownids[i], knowntype[i], " Known")
                    # print(knownids[j], " Known")
                    # for k in knownids:
                    path = '/static/FD/Face_recognition/'+ttt
                    # dt = datetime.now().strftime("")
                    # qrycheck = "select * from detection_details where date="
                    if knowntype[i]=='Criminal':
                        qry = "insert into detection_details(`criminal_id`,`date`,`img`,`time`,`ulid`,`type`) values('"+str(knownids[j])+"',curdate(),'"+path+"',curtime(),'0','Criminal')"
                        db.insert(qry)
                    else:
                        qry = "insert into detection_details(`misid`,`date`,`img`,`time`,`ulid`,`type`) values('" + str(
                            knownids[j]) + "',curdate(),'" + path + "',curtime(),'0','Missing')"
                        db.insert(qry)

                import time
                time.sleep(60)

detect_face_post()