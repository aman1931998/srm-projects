from tkinter import *
import random
root= Tk()

canvas = Canvas(width = 1000, height = 800, bg = 'yellow')
root.resizable(width=False, height=False)

canvas.pack(expand = YES, fill = BOTH)

gif1 = PhotoImage(file = 'ludo board.gif')
canvas.create_image(50, 10, image = gif1, anchor = NW)


g3 = canvas.create_oval(50,290,80,320, outline="green", fill="green", tags="oval")
        #40, 380, 90, 430
g4 = canvas.create_oval(50,390,80,420, outline="green", fill="green", tags="oval")

drag_data = {"x": 0, "y": 0, "item": None}
init_data = {"x": 0, "y": 0, "item": None}
final_coordinate = [0, 0]


def OnTokenButtonPress(event):
    # record the item and its location
    drag_data["item"] = canvas.find_closest(event.x, event.y)[0]
    drag_data["x"] = event.x
    drag_data["y"] = event.y

    init_data["item"] = drag_data["item"]  # defining new destination
    init_data["x"] = drag_data["x"]
    init_data["y"] = drag_data["y"]

    item_below = canvas.find_overlapping(event.x, event.y, event.x, event.y)[0]




def OnTokenButtonRelease(event):
    drag_data["item"] = None
    drag_data["x"] = 0
    drag_data["y"] = 0


def OnTokenMotion(event):
    moved_x = event.x - drag_data["x"]
    moved_y = event.y - drag_data["y"]

    canvas.move(drag_data["item"], moved_x, moved_y)

    drag_data["x"] = event.x
    drag_data["y"] = event.y
    if drag_data["x"]>=444 and drag_data["x"]<=582 and drag_data["y"]>=330 and drag_data["y"]<462:
        print ('pug gayi')


canvas.tag_bind("oval", "<ButtonPress-1>", OnTokenButtonPress)
canvas.tag_bind("oval", "<B1-Motion>", OnTokenMotion)
class RollTheDice:
    def __init__(self, parent):
        self.dieParent = parent
        self.dieContainer = Frame(parent).pack()

        self.dieLabel = Label(self.dieContainer, text="Number of Dice you will be rolling:")
        self.dieLabel.pack(side=TOP)

        self.dieEntry = Entry(self.dieContainer)
        self.dieEntry.pack(side=TOP)

        self.sideLabel = Label(self.dieContainer, text="Number of Sides per Die:")
        self.sideLabel.pack(side=TOP)

        self.sideEntry = Entry(self.dieContainer)
        self.sideEntry.pack(side=TOP)

        global rolldisp
        rolldisp = StringVar()
        self.rollResult = Label(self.dieContainer, textvariable=rolldisp)
        self.rollResult.pack(side=TOP)

        self.diceButton = Button(self.dieContainer)
        self.diceButton.configure(text="Roll the Dice!", background="orangered1")
        self.diceButton.pack(side=LEFT)
        self.diceButton.bind("<Button-1>", self.diceButtonClick)
        self.diceButton.bind("<Return>", self.diceButtonClick)

        self.quitButton = Button(self.dieContainer)
        self.quitButton.configure(text="Quit", background="blue")
        self.quitButton.pack(side=RIGHT)
        self.quitButton.bind("<Button-1>", self.quitButtonClick)
        self.quitButton.bind("<Return>", self.quitButtonClick)

    def diceButtonClick(self, event):
        die = int(self.dieEntry.get())
        side = int(self.sideEntry.get())
        DieRoll(die, side)

    def quitButtonClick(self, event):
        self.dieParent.destroy()

def DieRoll(dice, sides):
    import random
    rollnumber = 1
    runningtotal = 0
    endresult = ""
    while rollnumber <= dice:
        roll = random.randint(1, sides)
        endresult += "Roll #"
        endresult += str(rollnumber)
        endresult += ": "
        endresult += str(roll)
        endresult += "\n"
        runningtotal += roll
        rollnumber += 1
    finalresult = "Your Roll:\n"
    finalresult += endresult
    rolldisp.set(finalresult)

def leftClick(event):
    x = root.winfo_pointerx() 
    y = root.winfo_pointery() 

    print("Click at: ",x,y)

root.bind("<Button-1>", leftClick)

root = Tk()
root.title("Die Roller")
myapp = RollTheDice(root)
root.mainloop()
