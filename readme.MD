#LeanToat

##Android library to add a lean toast in your app.


##Usage

###Showing a lengthy lean toast.

```
    LeanToast.show(LeanToast.this,"Showing LeanToast"+(time),LeanToast.LENGTH_LONG);
```

###Showing a shorter lean toast.

```
    LeanToast.show(LeanToast.this,"Showing LeanToast"+(time),LeanToast.LENGTH_SHORT);
```

###Changing the color of lean toast.

```
    LeanToast.setColor(Color.parseColor(colorHexString));
```
