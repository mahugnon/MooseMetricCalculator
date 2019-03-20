Class {
	#name : #MMCQueryAll,
	#superclass : #MMCAbstractQuery,
	#category : #'Moose-Metric-Calculator-Query'
}

{ #category : #accessing }
MMCQueryAll class >> displayName [
	^ 'queryAll:'
]

{ #category : #accessing }
MMCQueryAll >> searchAssociations [
	^ self searchAssociationWithoutAssociationClass 
]
