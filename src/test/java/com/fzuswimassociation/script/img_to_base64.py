import base64
from PIL import Image
from io import BytesIO
import requests

response = requests.get('https://news.fzu.edu.cn/__local/0/64/DA/870A4923BED027AA85ECB0B6A4F_FC37EBFB_270AE.jpg')

img = Image.open(BytesIO(response.content))
img_base64 = base64.b64encode(response.content).decode()

print(img_base64)