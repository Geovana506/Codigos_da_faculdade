from flask import Flask, render_template, request
import math

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def index():
    resultado = None

    if request.method == "POST":
        try:
            a = float(request.form["a"])
            b = float(request.form["b"])
            c = float(request.form["c"])

            delta = b**2 - 4*a*c

            if delta > 0:
                x1 = (-b + math.sqrt(delta)) / (2*a)
                x2 = (-b - math.sqrt(delta)) / (2*a)
                resultado = f"Δ = {delta:.2f}, x1 = {x1:.2f}, x2 = {x2:.2f}"

            elif delta == 0:
                x = -b / (2*a)
                resultado = f"Δ = {delta:.2f}, x = {x:.2f}"

            else:
                resultado = f"Δ = {delta:.2f}, não existem raízes reais."

        except:
            resultado = "Erro: insira valores válidos."

    return render_template("index.html", resultado=resultado)

if __name__ == "__main__":
    app.run(debug=True)