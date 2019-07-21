# author: kwalsh@holycross.edu
# Estimate optimal placement of margin notes.
# Each note has a natural position (i.e. centered on some line of the page), but
# also has a height and can't overlap neighboring notes. We place
# each note some distance from its natural position, such that none of the notes
# overlap, while minimizing the sum-squared distances each note must move.
# This is equivalent to solving a spring-mass system and solving for minimum
# potential energy.
#
# NS: added syntax requirements for current python.
# On OS X, use pythonw (rather than python) to execute this script.

import numpy as np
import scipy.optimize as opt
import matplotlib.pyplot as plt
import matplotlib.patches as patches

# Fixed position of each line. = DenseVector(1,2,3,4) in Breeze
L = np.array([0.1, 0.3, 0.4, 0.6, 0.7, 0.8])

# Fixed height of each margin note.
H = np.array([0.2, 0.1, 0.2, 0.05, 0.05, 0.2])

# Number of notes. = L.size in Breeze
n = np.size(L)

# Initial value for space before each margin note.  =  DenseVector.zeros[Double](n) in Breeze
P0 = np.zeros(n)

# This function displays the margin notes and lines graphically.
def show_notes(P):
    x = np.arange(0, 6)
    print("\tnp.arrange == {}".format( x))
    print ("\tPositions of iliad lines == {}".format (L))
    print ("\tHeight of margin note / 2 == {}".format(H/2))
    fig, ax = plt.subplots(figsize=(6, 6))
    S = np.cumsum(P + H) - H

    print ("\tS values == {}".format(S))
    # plt.errorbar(x, np.cumsum(P + H) - H/2, yerr=H/2, fmt='x')
    for i in range(n):
        ax.add_patch(patches.Rectangle((i-0.5, S[i]), 1, H[i], alpha=0.1))
    ax.scatter(x, L)
    plt.show()

# The equilibrium state minimizes the total potential energy of the system.
def energy(P):
    # Calculate the ending position of each margin note. = accumulate(P+H) in Breeze
    E = np.cumsum(P + H)
    # Calculate the center position of each margin note.
    C = E - H/2
    # Calculate the distance of each note to its line.
    D = C - L
    # Potential energy is the sum of squared distances.  = sum(x) in Breeze
    return np.square(D).sum()

# Compute the potential energy of the initial system and show a diagram.
print ("\n'Energy' (initial?) == {}".format(energy(P0)))
#show_notes(P0)

print("")
print("")
print("")

# Now minimize using a optimization algorithm, under the constraint that the
# spaces between margin notes must be between 0 and 1.
bounds = np.repeat([[0, 1]], n, axis=0)
print("Define bounds: {}".format(bounds))
P1 = opt.minimize(energy, P0, method='L-BFGS-B', bounds=bounds).x


# Compute the potential energy of the (approx) optimal system and show a diagram.
print ("'Energy' (optimized?) == {}".format(energy(P1)))
show_notes(P1)
